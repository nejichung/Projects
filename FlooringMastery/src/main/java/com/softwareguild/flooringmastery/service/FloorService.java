/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.softwareguild.flooringmastery.daos.FlooringAuditDao;
import com.softwareguild.flooringmastery.daos.OrderDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.daos.ProductDao;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxesDao;
import com.softwareguild.flooringmastery.dtos.Order;
import com.softwareguild.flooringmastery.dtos.Product;
import com.softwareguild.flooringmastery.dtos.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software Guld
 */
public class FloorService {

    OrderDao orderDao;
    ProductDao productDao;
    TaxesDao taxesDao;

    public FloorService(OrderDao orderDao, ProductDao productDao, TaxesDao taxesDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxesDao = taxesDao;
//        this.auditDao = auditDao;
    }

    public AddOrderResponse AddOrder(LocalDate date,
            String name, String productType, String stateAbbreviation,
            BigDecimal area) {
        // set all values of variables in service and then send order to dao
        AddOrderResponse response = new AddOrderResponse();
        // in here we will take in the user inputted info and then 

        try {

            Order toAdd = new Order();
            List<Order> allOrders = orderDao.getOrdersByDate(date);

            validateName(name);

            validateProduct(productType);
//            } catch ( ex) {
//                response.setSuccess(false);
//                response.setMessage(ex.getMessage());

            //            validateArea(area);
            validateState(stateAbbreviation);
            validateArea(area);
//            } catch ( ex) {
//                response.setSuccess(false);
//                response.setMessage(ex.getMessage());
//            }
            validateDateForAdd(date);
            toAdd.setDate(date);
            toAdd.setOrderNumber(genNewOrderNumber(allOrders));
            toAdd.setName(name);

            Tax currentTax = taxesDao.checkState(stateAbbreviation);
            toAdd.setStateAbbreviation(stateAbbreviation);

            toAdd.setTaxRate(currentTax.getTaxRate());

            Product currentProduct = productDao.checkProduct(productType);
            toAdd.setProductType(productType);
            toAdd.setArea(area);
            toAdd.setMatUnitCost(currentProduct.getCostPerSquareFoot());
            toAdd.setLaborUnitCost(currentProduct.getLaborCostPerSquareFoot());

            orderDao.addOrder(toAdd); // eventuall pass in an order after we create the order from the viarables!!!
            response.setOrder(toAdd);
            response.setSuccess(true);
        } catch (OrderPersistenceException | ProductPersistenceException | TaxPersistenceException
                | InvalidDateException
                | InvalidProductException
                | InvalidStateException
                | InvalidAreaException
                | InvalidNameException //                | InvalidAreaException
                ex) {
            response.setSuccess(false); // we are alteringdate of hte set success to false
            response.setMessage(ex.getMessage()); // we are setting hte message of the resposne to what the message is in persistence exception

        }

        return response;
    }

    public ListOrderResponse DisplayOrder(LocalDate lookup) {
        ListOrderResponse listResponse = new ListOrderResponse();
        try {
            // this is where we compare the order number they inputted to a oder in the list

            List<Order> allOrders = orderDao.getOrdersByDate(lookup);
            if (allOrders == null || allOrders.size() == 0) {
                listResponse.setSuccess(false);
                listResponse.setMessage("That date has no orders on it.");
            } else if (listResponse == null) { // if the ListOrderResponse is null
                listResponse.setSuccess(false);
                listResponse.setMessage("Couldn't find orders for that date.");
            } else {
                listResponse.setSuccess(true);
                listResponse.setAllOrders(allOrders);

            }

        } catch (OrderPersistenceException ex) {
            listResponse.setSuccess(false);
            listResponse.setMessage(ex.getMessage());
        }
        return listResponse;
    }

    private boolean validateState(String stateAbbreviation) throws InvalidStateException {
        boolean success = false;

        if (stateAbbreviation.equals("")) {
            success = false;
        } else {
            try {
                List<Tax> allTaxes = taxesDao.getTaxes();
                boolean found = false;
                for (Tax toCheck : allTaxes) {
                    found = stateAbbreviation.equalsIgnoreCase(toCheck.getStateAbbreviation());
                    if (found) {

                        success = true;
                        break;
                    }
                }
                if (!found) {
                    throw new InvalidStateException("You entered an invalid state");

                }
            } catch (TaxPersistenceException ex) {
                //????
                success = false;
            }
        }
        return success;
    }

    private boolean validateProduct(String productType) throws InvalidProductException {
        boolean success = false;

        if (productType.equals("")) {
            success = false;
        } else {
            try {
                List<Product> allProducts = productDao.getProducts();
                boolean found = false;
                for (Product toCheck : allProducts) {
                    found = productType.equalsIgnoreCase(toCheck.getProductType());
                    if (found) {

                        success = true;
                        break;
                    }
                }
                if (!found) {
                    throw new InvalidProductException("You entered an invalid Prodcut");
                }
            } catch (ProductPersistenceException ex) {
                //???
                success = false;
            }
        }
        return success;
    }

    public RemoveOrderResponse RemoveOrder(int orderRemoveNumber, LocalDate orderRemoveDate) {
        RemoveOrderResponse toReturn = new RemoveOrderResponse();
        try {

            List<Order> allOrders = orderDao.getOrdersByDate(orderRemoveDate);

            boolean isValidId = checkOrderNumber(orderRemoveNumber, orderRemoveDate);
            try {
                validateDateForEdit(orderRemoveDate);
            } catch (InvalidDateException ex) {
                toReturn.setSuccess(false);
                toReturn.setMessage(ex.getMessage());
            }
            toReturn.setSuccess(isValidId);
            if (isValidId) {

                orderDao.removeOrder(orderRemoveDate, orderRemoveNumber);

            } else {
                toReturn.setMessage("No Order file was found");
            }

        } catch (OrderPersistenceException ex) {
            toReturn.setSuccess(false);
            toReturn.setMessage(ex.getMessage());
        }
        return toReturn;
    }

    private boolean checkOrderNumber(int orderNumber, LocalDate displayOrder) {
        boolean isValid = false;

        try {
            List<Order> allOrders = orderDao.getOrdersByDate(displayOrder);
            // takingin an order number that they iinputted and then we will compare that to the list of all orders in that date
            // look for a match, if match is found we will return that orderNumber

            for (Order currentOrder : allOrders) {
                if (orderNumber == currentOrder.getOrderNumber()) {
                    isValid = true;
                    break;
                }
            }

        } catch (OrderPersistenceException ex) {
            //???
            isValid = false;
        }
        return isValid;
    }

    public EditOrderResponse editOrder(Order toEdit) {
        EditOrderResponse response = new EditOrderResponse();
        boolean success = true;
        try {

//        if (success) {
            validateDateForEdit(toEdit.getDate());
            validateName(toEdit.getName());
            validateState(toEdit.getStateAbbreviation());
            validateProduct(toEdit.getProductType());
            validateArea(toEdit.getArea());

            Tax currentTax = taxesDao.checkState(toEdit.getStateAbbreviation());
            toEdit.setStateAbbreviation(toEdit.getStateAbbreviation());
            toEdit.setTaxRate(currentTax.getTaxRate());

            Product currentProduct = productDao.checkProduct(toEdit.getProductType());
            toEdit.setProductType(toEdit.getProductType());
            toEdit.setArea(toEdit.getArea());
            toEdit.setMatUnitCost(currentProduct.getCostPerSquareFoot());
            toEdit.setLaborUnitCost(currentProduct.getLaborCostPerSquareFoot());

            Order orderToEdit;
            try {
                orderToEdit = orderDao.editOrder(toEdit);
                response.setEditedOrder(orderToEdit);
                response.setSuccess(true);
            } catch (OrderPersistenceException ex) {
                Logger.getLogger(FloorService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (InvalidDateException | InvalidStateException | InvalidAreaException
                | InvalidNameException | InvalidProductException | ProductPersistenceException | TaxPersistenceException ex) {
//            auditDao.writeAuditEntry("InvalidDateException caught.");
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    private void validateDateForEdit(LocalDate orderRemoveDate) throws InvalidDateException {    // EDIT // we will need one for the ADD
        try {
            // we needto see if this date matches with an order
            List<Order> allOrders = orderDao.getOrdersByDate(orderRemoveDate);

            if (allOrders == null || allOrders.isEmpty()) {
                throw new InvalidDateException(orderRemoveDate + "is not a valid date.");
                //sad;fld
            }

            // if (the all orders is empty or null then it will fail
        } catch (OrderPersistenceException ex) {
//            auditDao.writeAuditEntry("OrderPersistenceException caught.");
        }
    }

    private void validateName(String name) throws InvalidNameException {
        if (name.equalsIgnoreCase("")) {
            throw new InvalidNameException(name + "is not a valid item name.");
        }

    }

    private void validateArea(BigDecimal area) throws InvalidAreaException { // ASK
        if (area.compareTo(BigDecimal.ZERO) <= 0 || area.equals("")){
            throw new InvalidAreaException(area + "is not a valid area.");
        }
    }

    public ProductListResponse getAllProducts() {
        ProductListResponse productResponse = new ProductListResponse();
        try {

            List<Product> allProducts = productDao.getProducts();
            productResponse.setSuccess(true);
            productResponse.setAllProducts(allProducts);

        } catch (ProductPersistenceException ex) {
            productResponse.setSuccess(false);
            productResponse.setMessage(ex.getMessage());
//            auditDao.writeAuditEntry("ProductPersistenceException caught.");
        }
        return productResponse;
    }

    public TaxListResponse getAllTaxes() {
        TaxListResponse taxResponse = new TaxListResponse();

        try {
            List<Tax> allTaxes = taxesDao.getTaxes();
            taxResponse.setSuccess(true);
            taxResponse.setAllTaxes(allTaxes);
        } catch (TaxPersistenceException ex) {
            taxResponse.setSuccess(false);
            taxResponse.setMessage(ex.getMessage());
//            auditDao.writeAuditEntry("ProductPersistenceException caught.");

        }
        return taxResponse;
    }

    private void validateDateForAdd(LocalDate date) throws InvalidDateException { // has to be after now
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidDateException(date + "is not a valid Date to add");

        }

    }

    private int genNewOrderNumber(List<Order> allOrders) {
        int genOrderNumber = 0;
        if (allOrders.isEmpty()) {
            genOrderNumber = 1;
        } else {
            for (Order toCheck : allOrders) {
                if (toCheck.getOrderNumber() > genOrderNumber) {
                    genOrderNumber = toCheck.getOrderNumber();
                }
            }
            genOrderNumber++;
        }
        return genOrderNumber;
    }

}

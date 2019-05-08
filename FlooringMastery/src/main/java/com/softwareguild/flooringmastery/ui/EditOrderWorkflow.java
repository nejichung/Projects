/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import com.softwareguild.flooringmastery.service.EditOrderResponse;
import com.softwareguild.flooringmastery.service.FloorService;
import com.softwareguild.flooringmastery.service.ListOrderResponse;
import static com.softwareguild.flooringmastery.ui.MainMenu.ui;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
class EditOrderWorkflow {

    FloorService service;

    EditOrderWorkflow(FloorService service) {
        this.service = service;
    }
    public void run(ConsoleIO ui){
        ui.print("EditOrderWorkflow testing\n");

        LocalDate date = ui.readDate("Date(MM/dd/yyyy): ");
        ListOrderResponse listResponse = service.DisplayOrder(date);  // getting list of Orders by the Date   
        
        if (listResponse.getSuccess()){ // if it's successful
            Order toEdit = getOrderByOrderNumber(listResponse.getAllOrders()); // we will make an Order object thru this method
        
        String newLastName = ui.readString("Please enter new last name (blank to keep): " + toEdit.getName() + ")");
        if(!newLastName.isEmpty()){
            toEdit.setName(newLastName);
        }
        String newStateAbbreviation = ui.readString("Please enter new state abbreviation (blank to keep): " + toEdit.getStateAbbreviation() + ")");
        if(!newStateAbbreviation.isEmpty()){
//            service.validateState(newStateAbbreviation);
            toEdit.setStateAbbreviation(newStateAbbreviation);
        
        }
        String newProductType = ui.readString("Please enter new product type (blank to keep): " + toEdit.getProductType() + ")");
        if(!newProductType.isEmpty()){
//            service.validateProduct(newProductType);
            toEdit.setProductType(newProductType);

    }
        String newArea = ui.readString("Please enter new area (blank to keep): " + toEdit.getArea() + ")");
        if(!newArea.isEmpty()){
            BigDecimal area = new BigDecimal(newArea);
            toEdit.setArea(area); // GET TO THAT
        }
        
        EditOrderResponse response = service.editOrder(toEdit);
        
        if (response.getSuccess()){
            Order editedOrder = response.getEditedOrder();
            displayOrder(editedOrder);
        } else {
            ui.print("Error: " + response.getMessage());
        }
//    private Order getOrderByOrderNumber(List<Order> allOrders) {
//       ;
//    }
        } else {
            ui.print("BIG ERROR: " + listResponse.getMessage());
        }
    }
    private Order getOrderByOrderNumber ( List<Order> allOrders) {
        Order toReturn = null;
        
        if(allOrders.size() > 0){
            boolean found = false;
            while (!found){
                int orderNumber = ui.readInt("Enter the order number of the order you want to edit: ");
                for (Order toCheck : allOrders){
                    if (orderNumber == toCheck.getOrderNumber()){
                        toReturn = toCheck;
                        found = true;
                        break;
                    }
                }
            }
        } return toReturn;
    }

    private void displayOrder(Order editedOrder) {
         ui.print( editedOrder.getOrderNumber() + "\n" );
        ui.print(editedOrder.getName() + "\n" );
        ui.print(editedOrder.getStateAbbreviation() + "\n");
        ui.print(editedOrder.getTaxRate() + "\n");
        ui.print(editedOrder.getProductType() + "\n");
        ui.print(editedOrder.getArea() + "\n");
        ui.print(editedOrder.getMatUnitCost() + "\n");
        ui.print(editedOrder.getLaborUnitCost() + "\n");
        ui.print(editedOrder.getTotalMatCost() + "\n");
        ui.print(editedOrder.getTotalLaborCost() + "\n");
        ui.print(editedOrder.getTotalTax() + "\n");
        ui.print(editedOrder.getOrderTotal() + "\n");
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.service.TaxListResponse;
import com.softwareguild.flooringmastery.service.ProductListResponse;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import com.softwareguild.flooringmastery.dtos.Product;
import com.softwareguild.flooringmastery.dtos.Tax;
import com.softwareguild.flooringmastery.service.AddOrderResponse;
import com.softwareguild.flooringmastery.service.FloorService;
import static com.softwareguild.flooringmastery.ui.MainMenu.ui;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AddOrderWorkflow {

    FloorService service;
//  constructor to gain access to the service

    public AddOrderWorkflow(FloorService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {
        Order toCreate = new Order();
        LocalDate date = getDate();
        //OrderNumber,
        int orderName;
        //CustomerName,
        String name = getName();
        //   StateAbbreviation
        TaxListResponse taxResponse = service.getAllTaxes();
        String stateAbbreviation = getStateAbbreviation(taxResponse.getAllTaxes());
        //ProductType,
        ProductListResponse prodResponse = service.getAllProducts();
        String productType = getProductType(prodResponse.getAllProducts());
        //Area,
        BigDecimal area = getArea();
        //CostPerSquareFoot,
        BigDecimal matUnitCost;
        //LaborCostPerSquareFoot
        BigDecimal laborUnitCost;

        toCreate.setName(name);
        toCreate.setStateAbbreviation(stateAbbreviation);
        toCreate.setProductType(productType);
        toCreate.setArea(area);
//        toCreate.setMatUnitCost(matUnitCost);
//        toCreate.setLaborUnitCost(laborUnitCost);

        AddOrderResponse response = service.AddOrder(date, name, productType, stateAbbreviation, area);
    }

    private LocalDate getDate() {
        LocalDate date;

        date = ui.readDate("Date(MM/dd/yyyy): ", LocalDate.now(), LocalDate.MAX);
        return date;
    }

    private String getName() {
        String name;

        do {
            name = ui.readString("Last Name: ");
        } while (name.equals(""));

        return name;
    }

    private String getStateAbbreviation(List<Tax> allTaxes) {
        String stateAbbreviation;
        boolean success = false;
        do {
            stateAbbreviation = ui.readString("State Abbreviation(OH, PA, MI, IN): ");
            for (Tax toCheck : allTaxes) {
                if (toCheck.getStateAbbreviation().equalsIgnoreCase(stateAbbreviation)) {
                    stateAbbreviation = toCheck.getStateAbbreviation();
                    success = true;
                }
            }

        } while (!success);

        return stateAbbreviation;
    }

    private String getProductType(List<Product> allProducts) {
        String productType;
        boolean success = false;
        do {
            productType = ui.readString("Product Type(Carpet, Laminate, Tile , Wood): ");
            for (Product toCheck : allProducts) {
                if (toCheck.getProductType().equalsIgnoreCase(productType)) {
                    productType = toCheck.getProductType();
                    success = true;
                }
            }
        } while (!success);
        return productType;
    }

    private BigDecimal getArea() {
        BigDecimal area;

        do {
            area = ui.readBigDecimal("Area: ");
        } while (area.equals(""));
        return area;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import com.softwareguild.flooringmastery.service.FloorService;
import com.softwareguild.flooringmastery.service.ListOrderResponse;
import static com.softwareguild.flooringmastery.ui.MainMenu.ui;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
class DisplayOrderWorkflow {

    FloorService service;

    DisplayOrderWorkflow(FloorService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) throws OrderPersistenceException {
        LocalDate displayOrder = ui.readDate("Please enter the date(MM/dd/yyyy): ");
       
        ListOrderResponse response = service.DisplayOrder(displayOrder);
        if (!response.getSuccess()) { // if the response is not successful
            ui.print(response.getMessage()); // print out the message when it is not successful
            
        } else {
            List<Order> allOrders = response.getAllOrders();

            for (Order currentOrder : allOrders) {
                ui.print("Order Number: " + Integer.toString(currentOrder.getOrderNumber()));
                ui.print("\n");
                ui.print("Name: " + currentOrder.getName());
                ui.print("\n");
                ui.print("State: " + currentOrder.getStateAbbreviation());
                ui.print("\n");
                ui.print("Tax Rate: " + currentOrder.getTaxRate());
                ui.print("\n");
                ui.print("Product Type: " + currentOrder.getProductType());
                ui.print("\n");
                ui.print("Area: " + currentOrder.getArea());
                ui.print("\n");
                ui.print("Material Cost/Square Foot: $" + currentOrder.getMatUnitCost());
                ui.print("\n");
                ui.print("LaborCost/Square Foot: $" + currentOrder.getLaborUnitCost());
                ui.print("\n");
                ui.print("Total Material Cost: $" + currentOrder.getTotalMatCost());
                ui.print("\n");
                ui.print("Total Labor Cost: $" + currentOrder.getTotalLaborCost());
                ui.print("\n");
                ui.print("Total Tax: $" + currentOrder.getTotalTax());
                ui.print("\n");
                ui.print("Order Total Cost: $" + currentOrder.getOrderTotal().setScale(2, RoundingMode.HALF_UP));
                ui.print("\n");

            }
        }

    }

   
}

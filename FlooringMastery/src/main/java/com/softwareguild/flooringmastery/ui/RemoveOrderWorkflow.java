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
import com.softwareguild.flooringmastery.service.RemoveOrderResponse;
import static com.softwareguild.flooringmastery.ui.MainMenu.ui;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
class RemoveOrderWorkflow {
    FloorService service;

    RemoveOrderWorkflow(FloorService service) {
    this.service = service;    
    }
     public void run(ConsoleIO ui) throws OrderPersistenceException {
         
         LocalDate orderRemoveDate = ui.readDate("Please enter the date(MM/dd/yyyy): ");
//         orderRemoveDate = service.validateDate(orderRemoveDate);
        
         int orderRemoveNumber = ui.readInt("Please enter the order number: ");
        
         RemoveOrderResponse removeResponse = service.RemoveOrder(orderRemoveNumber, orderRemoveDate);
         ui.print(removeResponse.getMessage() + "\n");
//         if(!removeResponse.getSuccess()){
//             ui.print ("Failed to remove order because " + removeResponse.getMessage());
//         } else 
         
         
//         ListOrderResponse response = service.DisplayOrder(orderRemoveDate);
//        
//         
//         if (!response.getSuccess()) {
//             ui.print("Failed to get the list of orders");
//         } else {
//             List<Order> allOrders = response.getAllOrders();
//             
//             int orderNumber = getOrderNumberToRemoveByOrderNumber();
//         }
             
              
     }

}

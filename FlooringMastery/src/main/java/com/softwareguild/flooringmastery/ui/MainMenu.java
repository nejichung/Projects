/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.service.FloorService;
import com.softwareguild.flooringmastery.service.InvalidAreaException;
import com.softwareguild.flooringmastery.service.InvalidDateException;
import com.softwareguild.flooringmastery.service.InvalidNameException;
import com.softwareguild.flooringmastery.service.InvalidProductException;
import com.softwareguild.flooringmastery.service.InvalidStateException;

/**
 *
 * @author Software Guld
 */
public class MainMenu {
    
    static ConsoleIO ui = new ConsoleIO();
    
    public static void run(FloorService service) throws ProductPersistenceException, TaxPersistenceException, InvalidAreaException, InvalidDateException, InvalidNameException, InvalidProductException, InvalidStateException, OrderPersistenceException {
        boolean shouldExit = false;
        
        while (!shouldExit){
            ui.print("------------------------\n");
            ui.print("1. Add an Order.\n");
            ui.print("2. Remove an Order.\n");
            ui.print("3. Edit an Order.\n");
            ui.print("4. Display an Order.\n");
            ui.print("5. Save Work.\n");
            ui.print("6. Quit.\n");
            
            int userChoice = ui.readInt("Please choose an action: ", 1, 6);   
        
            switch (userChoice){
                case 1:
                    addOrder(ui, service);
                    break;
                case 2:
                    removeOrder(ui, service);
                    break;
                case 3:
                    editOrder(ui, service);
                    break;
                case 4:
                    displayOrder(ui, service);
                    break;
                case 5:
                    saveWork(ui, service);
                    break;
                case 6:
                    shouldExit = true;
                    break;          
            }
}
        }

    private static void addOrder(ConsoleIO ui, FloorService service) throws ProductPersistenceException, TaxPersistenceException, InvalidAreaException, InvalidDateException, InvalidNameException, InvalidProductException, InvalidStateException {
        AddOrderWorkflow addFlow = new AddOrderWorkflow(service);
        addFlow.run(ui);
    }

    private static void removeOrder(ConsoleIO ui, FloorService service) throws OrderPersistenceException {
        RemoveOrderWorkflow removeFlow = new RemoveOrderWorkflow(service);
        removeFlow.run(ui);
    }

    private static void editOrder(ConsoleIO ui, FloorService service) throws OrderPersistenceException, TaxPersistenceException, ProductPersistenceException {
        EditOrderWorkflow editFlow = new EditOrderWorkflow(service);
        editFlow.run(ui);
    }

    private static void displayOrder(ConsoleIO ui, FloorService service) throws OrderPersistenceException {
        DisplayOrderWorkflow displayFlow = new DisplayOrderWorkflow(service);
        displayFlow.run(ui);
    }

    private static void saveWork(ConsoleIO ui, FloorService service){ {
        SaveWorkWorkflow saveFlow = new SaveWorkWorkflow(service);
        saveFlow.run(ui);
    }
    }
}

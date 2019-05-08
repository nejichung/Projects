/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import ServiceLayer.DvdService;
import java.util.Scanner;
import ui.AddDvdWorkflow;
import ui.ConsoleIO;
import ui.EditDvdWorkflow;
import ui.ListDvdWorkflow;
import ui.RemoveDvdWorkflow;
import ui.SearchDvdWorkflow;
import ui.DisplayDvdWorkflow;

/**
 *
 * @author Software Guld
 */
public class MovieLibrary { // Main Class

    static ConsoleIO ui = new ConsoleIO(); // getting that young DvdService

    public static void run(DvdService service) {
        
        boolean isDone = false; // set to false
        
        while( !isDone ){
            int userChoice = getUserChoice();
            
            switch( userChoice ){
                case 1:
                    AddDvdWorkflow addFlow = new AddDvdWorkflow(service); // gonna go to the addDvdWorkflow
                    addFlow.run(ui);
                    break; // break out of switch
                    
                case 2:
                    RemoveDvdWorkflow removeFlow = new RemoveDvdWorkflow(service);
                    removeFlow.run(ui);
                    break;
                    
               case 3: 
                    EditDvdWorkflow editFlow = new EditDvdWorkflow(service);
                    editFlow.run(ui);
                    break;
                case 4: 
                    ListDvdWorkflow listFlow = new ListDvdWorkflow(service);
                    listFlow.run(ui);
                    break;
                case 5: 
                    DisplayDvdWorkflow showFlow = new DisplayDvdWorkflow(service);
                    showFlow.run(ui);
                    break;
                case 6: 
                    SearchDvdWorkflow searchFlow = new SearchDvdWorkflow(service);
                    searchFlow.run(ui);
                    break;
                case 7: 
                    isDone = true;
                    break;
                    
            }
        }

      

    }

   



    private static int getUserChoice() {
        
        ui.print("1. Add DVD\n" );     //all of the choices the user has
        ui.print("2. Remove DVD\n");
        ui.print("3. Edit DVD\n");
        ui.print("4. List all DVDs\n");
        ui.print("5. Show DVD details\n");
        ui.print("6. Search for DVD by title\n");
        ui.print("7. Quit\n");;
        
        int toReturn = ui.readInt("Please select a choice between 1 and 7: ", 1, 7);
        
        return toReturn;
             
    }
}

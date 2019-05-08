/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import DTOs.Dvd;
import ServiceLayer.DvdService;
import ServiceLayer.ListDvdResponse;
import ServiceLayer.RemoveDvdResponse;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class RemoveDvdWorkflow {
    
    DvdService service;

    public RemoveDvdWorkflow(DvdService service) {
       this.service = service;
    }
    public void run(ConsoleIO ui) {        
        ListDvdResponse allDvdResponse = service.listDvds();
        
        if (!allDvdResponse.getSuccess()) {
            ui.print("SEVERE ERROR: " + allDvdResponse.getMessage());
        } else {
            
            List<Dvd> allDvds = allDvdResponse.getAllMovies();
            
            int id = getIdToRemoveByTitle(ui, allDvds);
            
            RemoveDvdResponse response = service.removeDvd(id);
            
            
        
   }   
}

     private int getIdToRemoveByTitle(ConsoleIO ui, List<Dvd> allMovies) {
        int toReturn = Integer.MIN_VALUE;

        //1. get a title from the user to be removed
        //2. look through all titles for the matching id
        boolean found = false;

        while (!found) {
            String title = ui.readString("Please enter the title to remove: ");

            for (Dvd toCheck : allMovies) {
                found = toCheck.getTitle().equalsIgnoreCase(title);
                if (found) {
                    //since we found a matching title
                    //here we want to save the id of that title
                    toReturn = toCheck.getId();
                    break;
                }
            }
        }

        return toReturn;
    }
}

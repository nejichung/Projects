/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import DTOs.Dvd;
import ServiceLayer.DvdService;
import ServiceLayer.EditDvdResponse;
import ServiceLayer.ListDvdResponse;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class EditDvdWorkflow {
    
    DvdService service;

    public EditDvdWorkflow(DvdService service) {
        this.service = service;
        
    }

    public void run(ConsoleIO ui) {
        
        ListDvdResponse listResponse = service.listDvds(); // grabbing the list of dvds from DvdService
      
        if (listResponse.getSuccess()){
        Dvd toEdit = getDvdToEditByTitle (ui, listResponse.getAllMovies()); // WHY IS THAT INSIDE THE ()? 
        
        
        String newTitle = ui.readString("Please enter new title (blank to keep: " + toEdit.getTitle() + ")");
        if (!newTitle.isEmpty()){
            toEdit.setTitle(newTitle);
        }
        
        int newYear = getYear(ui, toEdit.getReleaseYear());
        toEdit.setReleaseYear(newYear);
        
        String newRating = getRating(ui, toEdit.getRating());
        toEdit.setRating(newRating);
        
        String newDirector = ui.readString("Please enter new director(blank to keep: " + toEdit.getDirector() + ")");
        if (!newDirector.isEmpty()){
            toEdit.setDirector(newDirector);
        }
        
        String newStudio = ui.readString("Please enter new director (blank to keep: " + toEdit.getDirector() + ")");
        if (!newStudio.isEmpty()){
            toEdit.setStudio(newStudio);
        }
        
        String newNote = ui.readString("Please enter new note (blank to keep: " + toEdit.getNote() + ")");
        if (!newNote.isEmpty()){
            toEdit.setNote(newNote);
        }
        
        EditDvdResponse response = service.editDvd(toEdit); // sending to service layer
        
        if (response.getSuccess()) {
            Dvd editedDvd = response.getEditedDvd();
            displayMovie(ui, editedDvd);
        } else {
            ui.print("Error: " + response.getMessage());
       } 
    
    } else { 
            ui.print("SEVERE ERROR: " + listResponse.getMessage()); // couldn't list movies will show serious error
        }
    }

    private Dvd getDvdToEditByTitle(ConsoleIO ui, List<Dvd> allDvds) {
       Dvd toReturn = null; // set our toReturn to null
       
       if(allDvds.size() > 0) { // if the size of the list of movies is > 0
           boolean found = false; // setting boolean to false
           while (!found){ // as long as it's false
               String title = ui.readString("Please enter the title to edit: "); // this is what we prompt them to grab the dvd they want to edit
                       for (Dvd toCheck : allDvds) { // compare the title to the list of dvds
                           if(title.equalsIgnoreCase(toCheck.getTitle())){
                           toReturn = toCheck;  // if the 
                           found = true;
                           break;
                       }
               
           }
           
       }
       }
       
       return toReturn;
    }

    private int getYear(ConsoleIO ui, int releaseYear) {
        int toReturn = releaseYear;
        
        boolean validYear = false;
        while(!validYear){                                                              // this is the current year
            String newYear = ui.readString("Please enter new year (blank to keep: " + releaseYear + ")");
            if (newYear.isEmpty()){  // if they don't input a new year
                toReturn = releaseYear; // it will keep it's current year
                validYear = true;
            } else { // if they typed a new year then we need to test if it's valid
                
                try
                {
                     toReturn = Integer.parseInt(newYear);
                     if( toReturn >= 1900 && toReturn <= 2019) {
                         validYear = true;
                     }
                }
                catch( NumberFormatException ex){ // here we eat the exception?
            }
        }
    }
        return toReturn;
    }

    private String getRating(ConsoleIO ui, String rating) {
       String toReturn = null;
       
       boolean validRating = false;
       while(!validRating){
           String newRating = ui.readString("Please enter new rating (blank to keep: " + rating);
           if (newRating.isEmpty()){
               toReturn = rating; // if nothing is inputted then the rating will stay the same
               validRating = true; // break out of the loop
           } else {
               toReturn = newRating; // if somethign is inputted, the newrating will be the new rating
               validRating =    // these are the valid ratings.
                       newRating.equalsIgnoreCase("G") ||
                       newRating.equalsIgnoreCase("PG") ||
                       newRating.equalsIgnoreCase("PG-13") ||
                       newRating.equalsIgnoreCase("R") ||
                       newRating.equalsIgnoreCase("Unrated");
                       
           }
               
               }
       
       return toReturn;
    }

    private void displayMovie(ConsoleIO ui, Dvd editedMovie) {
        ui.print( editedMovie.getTitle() + "\n" );
        ui.print(editedMovie.getId() + "\n" );
        ui.print(editedMovie.getReleaseYear() + "\n");
        ui.print(editedMovie.getRating() + "\n");
        ui.print(editedMovie.getDirector() + "\n");
        ui.print(editedMovie.getStudio() + "\n");
        ui.print(editedMovie.getNote() + "\n");
    }
    
}


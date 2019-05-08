/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceLayer;

import DAOs.DvdDao;
import DAOs.FileDao;
import DTOs.Dvd;
import java.util.ArrayList;
import java.util.List;

/*
/**
 *
 * @author Software Guld
 */
public class DvdService { // supposed to validate the movies properties
    DvdDao dao;   // fileRepo object named it dao
    public DvdService(DvdDao dao){
        this.dao = dao; // updating the dao variable
    }
    public AddDvdResponse addDvd(Dvd toAdd){ // object is Dvd 
         AddDvdResponse toReturn = new AddDvdResponse();
         
         //1. Validate the dvd
         boolean success = true;
         String message = validate( toAdd); 
         
         success = message.length() < 1;
         
         if (success) {
             //2. if valid, send to DAO
             //3. if add successful, send back success response w/ Dvd
             //4. else, send back error message from the DAO
             
             Dvd created = dao.addDvd(toAdd);
             
             if (created == null) { // if can't add dvd, will print it out
                toReturn.setSuccess(false);
                toReturn.setMessage("Failed to add Dvd.");
             } else {
                 toReturn.setMovie(created);
                 toReturn.setSuccess(true);
             }
         } else {
             toReturn.setMessage(message);
             toReturn.setSuccess(success);
         }
         
         return toReturn;
    }

    


                
    
    public RemoveDvdResponse removeDvd(int id) { // Ben : why is this int id?
        RemoveDvdResponse toReturn = new RemoveDvdResponse();


        List<Dvd> allMovies = dao.listDvds();

        boolean isValidId = validateId(id, allMovies);

        if (isValidId) {
            
            Dvd matching = dao.getDvdById(id);
            
            boolean success = dao.removeDvd(id); // if valid send the request off to the dao
            
            if(success) {
                // if successful, return success response
                toReturn.setSuccess(true);
                toReturn.setTitle( matching.getTitle());
            } else {
                // else, indicate dao failure
                toReturn.setSuccess(false);
                toReturn.setMessage("Failed to remove movie.");
            }
            
        }
        else
        {
            // id was not valid...
            toReturn.setSuccess(false);
            // need to tell user
            toReturn.setMessage("Invalid ID.");
        }
        return toReturn;
}
    
    public EditDvdResponse editDvd(Dvd toEdit) { // Main menu leads us to here if they press edit
        EditDvdResponse response = new EditDvdResponse();
        
        boolean success = true; // setting boolean to true?
        String message  = validate(toEdit);
        
        success = message.length() < 1; // checking that something was inputted
        
        if(success){
            
            Dvd edited = dao.editDvd( toEdit );
            if(edited == null){
                response.setMessage( " Error: could not edit movie.");
                response.setSuccess(false);               
            } else {
                response.setEditedDvd( edited );
                response.setSuccess(true);
                 }
            
        } else{
            response.setSuccess(false);
            response.setMessage(message);
        }
        return response;
    }
 

    private boolean validateId(int id, List<Dvd> allMovies) {
        
        boolean isValid = false;
                                                    //if the id matches with something in the 
                                                    //list then it will remove that
        for( Dvd toCheck : allMovies ){
            if( id == toCheck.getId() ){
                isValid = true;
                break;
            }
        }
        
        return isValid;
    }

    public ListDvdResponse listDvds() {
        ListDvdResponse toReturn = new ListDvdResponse();

        List<Dvd> allDvds = dao.listDvds();

        //we expect our dao to give an EMPTY (but non-null)
        //list if it succeeds but there are no movies
        if (allDvds == null) {
            //there was an error
            toReturn.setSuccess(false);
            toReturn.setMessage("Failed to retrieve dvds.");
        } else {
            toReturn.setSuccess(true);
            toReturn.setAllMovies(allDvds);
        }

        return toReturn;

   
}
         private String validate(Dvd toAdd) {
        boolean success = true;
        String message = "";
        
        if (toAdd.getTitle().equals("")) {
            success = false;
            message = "Title must be at least one character long.";
        }

        if (success && toAdd.getDirector().equals("")) {
            success = false;
            message = "Director must be at least one character long.";
        }

        if (success && toAdd.getStudio().equals("")) {
            success = false;
            message = "Director must be at least one character long.";
        }

        if (success
                && !(toAdd.getRating().equalsIgnoreCase("G")
                || toAdd.getRating().equalsIgnoreCase("PG")
                || toAdd.getRating().equalsIgnoreCase("PG-13")
                || toAdd.getRating().equalsIgnoreCase("R")
                || toAdd.getRating().equalsIgnoreCase("unrated"))) {
            success = false;
            message = "Rating must be G/PG/PG-13/R/or unrated";
        }

        //TODO: un-hard-code the current year
        if (success
                && (toAdd.getReleaseYear() < 1900
                || toAdd.getReleaseYear() > 2019)) {
            success = false;
            message = "Year must be between 1900 and 2019";
        }
         
        
         if (success && toAdd.getNote().equals("")) {
            success = false;
            message = "Note must be at least one character long.";
         }
        return message;
         
    
       
    }
         public List<Dvd> returnListDvds(){ // List<Dvd> is our our return type. gonna return list of dvd
             return dao.listDvds(); // going into the FileDao listDvds method and grabbing return
             
         }
         
         public List<Dvd> SearchDvd(String title){
             List<Dvd> allDvds = dao.listDvds(); // grabbing the dvd list return from the FileDAO
             
             List<Dvd> newListOfDvds = new ArrayList<Dvd>(); // made a new list called newListOfDvds that we are sending to the workflow
             
             for(Dvd currentDvd : allDvds ){ // this is getting all the dvds in the allDvds list
                 if (title.equals(currentDvd.getTitle())){  // if there is a title match
                     newListOfDvds.add(currentDvd);   // we will update the newListOfDvds that will contain the matching title
                 }
                         
                 
                 
             }
             
             return newListOfDvds;
         }
         public List<Dvd> DisplayDvd(int id){
             List<Dvd> allDvds = dao.listDvds(); // grabbing the dvd list return from the FileDAO
             
             List<Dvd> newListOfDvds = new ArrayList<Dvd>(); // made a new list called newListOfDvds that we are sending to the workflow
             
             for(Dvd currentDvd : allDvds ){ // this is getting all the dvds in the allDvds list
                 if (id == currentDvd.getId()){  // if there is a title match
                     newListOfDvds.add(currentDvd);   // we will update the newListOfDvds that will contain the matching title
                 }
                         
                 
                 
             }
             
             return newListOfDvds;
         }
         
}
     


       



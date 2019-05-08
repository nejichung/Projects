/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import DTOs.Dvd;
import ServiceLayer.AddDvdResponse;
import ServiceLayer.DvdService;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
public class AddDvdWorkflow {
    
    DvdService service;  // constructor??
    
    public AddDvdWorkflow( DvdService service ){ // if 1 : add movie is chosen
        this.service = service;        
    }
    
    public void run( ConsoleIO ui ) {
        Dvd toBuild = new Dvd();
        
        String title = getTitle(ui);  // will get title from user
        int year = getYear(ui); // will get year from user
        String director = getDirector(ui);
        String rating = getRating(ui);
        String studio = getStudio(ui);
        String note = getNote(ui);
        
        toBuild.setTitle(title); // we are setting the title of the Dvd to whatever they input
        toBuild.setReleaseYear(year);
        toBuild.setDirector(director);
        toBuild.setRating(rating);
        toBuild.setStudio(studio);
        toBuild.setNote(note);
        
        AddDvdResponse response = service.addDvd( toBuild );
        
        if( response.getSuccess() ){
            Dvd returnedMovie = response.getMovie();
            displayDvdDetails( returnedMovie, ui );
        }
        else {
            ui.print( "Error: " + response.getMessage() );
        
        
    }
    }


   private String getTitle(ConsoleIO ui) {
       String title;
       
       do{
           title = ui.readString("Please enter title: ");
       } while(title.equals( "" )); // if title is blank 
       
       return title;
        
    }
    private int getYear(ConsoleIO ui) {
       int year = Integer.MIN_VALUE; // set to value that is not possible
       
       year = ui.readInt("Please enter year: ", 1900, 2019); // has to be between 1900-2019 or else 
       
       return year;
    }
    
    private String getDirector(ConsoleIO ui) {
        String director;
        
        do{
            director = ui.readString("Please enter director: ");
        } while(director.equals( "" )); // if director is blank
        
        return director;
    }

    private String getRating(ConsoleIO ui) {
        String rating = null;
        
        boolean isValid = false;
        while( !isValid ){
        rating = ui.readString("Please enter rating (G/PG/PG-13/R/Unrated): ");
        
        isValid = 
                rating.equalsIgnoreCase( "G" ) ||
                    rating.equalsIgnoreCase( "PG" ) ||
                    rating.equalsIgnoreCase( "PG-13" ) ||
                    rating.equalsIgnoreCase( "R" ) ||
                    rating.equalsIgnoreCase( "unrated" );
    }
        return rating;
    }

    private String getStudio(ConsoleIO ui) {
      String studio;
      
      do{
          studio = ui.readString("Please enter studio name: ");
      } while(studio.equals( "" ));
      
      return studio;
    }

    private String getNote(ConsoleIO ui) {
        String note = ui.readString("Please enter note (optional): ");
        return note;
    }

    private void displayDvdDetails(Dvd movie, ConsoleIO ui) {
        
        ui.print( movie.getTitle() + "\n" );
        ui.print( "ID: " + movie.getId()+ "\n" );
        ui.print( "Year: " + movie.getReleaseYear()+ "\n" );
        ui.print( "MPAA: " + movie.getRating()+ "\n" );
        ui.print( "Director: " + movie.getDirector()+ "\n" );
        ui.print( "Studio: " + movie.getStudio()+ "\n" );
        ui.print( "\n" + movie.getNote()+ "\n" );
    }
}

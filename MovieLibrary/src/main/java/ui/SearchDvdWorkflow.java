/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import DTOs.Dvd;
import ServiceLayer.DvdService;
import ServiceLayer.ListDvdResponse;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
public class SearchDvdWorkflow {
    DvdService service;

    public SearchDvdWorkflow(DvdService service) {
        this.service = service;
    }

    public void run(ConsoleIO ui) {

        String searchTitle = getTitle(ui);
        List<Dvd> allDvds = service.SearchDvd(searchTitle); // updating a new list that will contain everything from searchDvd
        
        for(Dvd currentDvd : allDvds){ // for every dvd in this dvdlist, only loop once
           ui.print("Title: " + currentDvd.getTitle());
           ui.print("\n");
           ui.print("Release Year: " + Integer.toString(currentDvd.getReleaseYear())); // converted integer to string
           ui.print("\n");
           ui.print("ID #: " + Integer.toString(currentDvd.getId()));
           ui.print("\n");
           ui.print("Director: " + currentDvd.getDirector());
           ui.print("\n");
           ui.print("Rating: " + currentDvd.getRating());
           ui.print("\n");
           ui.print("Studio: " + currentDvd.getStudio());
           ui.print("\n");
           ui.print("Note: " + currentDvd.getNote());
           ui.print("\n");
           
          
        }
    }

    private String getTitle(ConsoleIO ui) {
        String searchTitle = null;
        do {
            searchTitle = ui.readString("What is the title of the movie that you want to see the details of?");
        } while (searchTitle.equals("")); // if title is blank 

        return searchTitle;

    }

}

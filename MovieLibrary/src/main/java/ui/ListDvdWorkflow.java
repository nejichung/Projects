/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import DAOs.DvdDao;
import DTOs.Dvd;
import ServiceLayer.DvdService;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class ListDvdWorkflow {
     DvdService serv;   // fileRepo object named it serv
    public ListDvdWorkflow(DvdService serv){
        this.serv = serv; // updating the serv variable
    }

    public void run(ConsoleIO ui) {
        
        List<Dvd> allDvds = serv.returnListDvds(); // this is pulling form DvdService aka Middle man
        
        
        for(Dvd currentDvd : allDvds){ // for every dvd in this dvdlist, only loop once
           ui.print(currentDvd.getTitle() + currentDvd.getDirector());
         
           ui.print("\n");
            
            
    }
    
}
}

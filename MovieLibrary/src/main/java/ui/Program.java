/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Controllers.MovieLibrary;
import DAOs.DvdDao;
import DAOs.FileDao;
import ServiceLayer.DvdService;

/**
 *
 * @author Jacob
 */
public class Program {
    public static void main(String[] args) {
        DvdDao dao = new FileDao( "dvds.txt");  // Fails when the dvd file exists and has no info
        DvdService service = new DvdService(dao);
        MovieLibrary.run(service);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
public class TaxesFileDao implements TaxesDao {
    
    String path = "Taxes\\Taxes.txt";
//    FlooringAuditDaoImpl auditDao;
//     public TaxesFileDao(FlooringAuditDaoImpl auditDao) {
//    
//        this.auditDao = auditDao;
//     }

    @Override
    public List<Tax> getTaxes() throws TaxPersistenceException {
        List<Tax> allTax = new ArrayList();
        try{
            
            Scanner reader = new Scanner(new BufferedReader(new FileReader(path)));
            
            reader.nextLine();
            
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                
                String[] cells = line.split(",");
                
                Tax toBuild = new Tax();
                
                toBuild.setStateAbbreviation(cells[0]);
                toBuild.setTaxRate(new BigDecimal(cells[1]));
                
                allTax.add(toBuild);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
//            auditDao.writeAuditEntry("FileNotFoundException caught.");
      
        }
       return allTax; 
    }
    
    public Tax checkState(String stateAbbreviation) throws TaxPersistenceException{ // here we will be doingsomething similar to the productfile dao and checking fora state match and then set our tax and state tot he matching state
        Tax toReturn = null;
        List<Tax> listOfTax = getTaxes(); // getting a list from method above
        
            for( Tax currentTax : listOfTax){
                if(stateAbbreviation.equalsIgnoreCase(currentTax.getStateAbbreviation())){
                    toReturn = currentTax;
                }
            }
        
             return toReturn;
        
        
       
    }
    // we will need eventually a getTax method to find method
    
}

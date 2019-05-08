/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxesDao;
import com.softwareguild.flooringmastery.dtos.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryTaxDao implements TaxesDao{
    List<Tax> allTaxes = new ArrayList();

    
    public InMemoryTaxDao(){
        
        Tax ohio = new Tax();
        ohio.setStateAbbreviation("OH");
        ohio.setTaxRate(new BigDecimal("6.25"));
        allTaxes.add(ohio);
        
        Tax missouri = new Tax();
        missouri.setStateAbbreviation("MI");
        missouri.setTaxRate(new BigDecimal("5.75"));
        allTaxes.add(missouri);
        
        Tax indiana = new Tax();
        indiana.setStateAbbreviation("IN");
        indiana.setTaxRate(new BigDecimal("6"));
        allTaxes.add(indiana);
    }
    @Override
    public List<Tax> getTaxes() throws TaxPersistenceException {     
        return allTaxes;
    }

    @Override
    public Tax checkState(String stateAbbreviation) throws TaxPersistenceException {
      Tax toReturn = null;
        List<Tax> listOfTax = getTaxes(); // getting a list from method above
        
            for( Tax currentTax : listOfTax){
                if(stateAbbreviation.equalsIgnoreCase(currentTax.getStateAbbreviation())){
                    toReturn = currentTax;
                }
            }
        
             return toReturn;
    }
}

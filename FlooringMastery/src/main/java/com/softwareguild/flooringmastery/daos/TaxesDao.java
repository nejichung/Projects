/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Tax;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface TaxesDao {
    public List<Tax> getTaxes() throws TaxPersistenceException;
    public Tax checkState(String stateAbbreviation) throws TaxPersistenceException;
    
}

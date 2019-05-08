/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxesDao;
import com.softwareguild.flooringmastery.dtos.Tax;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class TaxAlwaysFailDao implements TaxesDao{

    @Override
    public List<Tax> getTaxes() throws TaxPersistenceException {
        throw new TaxPersistenceException(null);
    }

    @Override
    public Tax checkState(String stateAbbreviation) throws TaxPersistenceException {
        throw new TaxPersistenceException(null);
    }
    
}

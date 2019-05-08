/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface SuperDao {

    public List<Super> getAllSupers() throws SuperPersistenceException;

    public Super getSuperByID(Integer id) throws SuperPersistenceException;

    public List<Super> getAllSupersForOrganization(Integer id) throws SuperPersistenceException;

    public List<Super> getAllSupersForSighting(Integer id) throws SuperPersistenceException;

    public Super addSuper(Super toAdd) throws SuperPersistenceException;

    public void deleteSuper(Integer id) throws SuperPersistenceException;

    public Super editSuper(Super sup) throws SuperPersistenceException;


    
}

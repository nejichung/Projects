/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Sighting;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface SightingDao {

    public List<Sighting> getAllSightings() throws SightingPersistenceException;

    public Sighting getSightingByID(Integer id) throws SightingPersistenceException;

    public List<Sighting> getAllSightingsForSuper(Integer id) throws SightingPersistenceException;

    public List<Sighting> getAllSightingsForLocation(Integer id) throws SightingPersistenceException;

    public Sighting addSighting(Sighting toAdd) throws SightingPersistenceException;

    public void deleteSighting(Integer id) throws SightingPersistenceException;

    public Sighting editSighting(Sighting sighting) throws SightingPersistenceException;
    
}

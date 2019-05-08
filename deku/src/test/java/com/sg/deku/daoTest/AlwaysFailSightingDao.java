/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.SightingDao;
import com.sg.deku.daos.SightingPersistenceException;
import com.sg.deku.models.Sighting;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AlwaysFailSightingDao implements SightingDao {

    @Override
    public List<Sighting> getAllSightings() throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public Sighting getSightingByID(Integer id) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public List<Sighting> getAllSightingsForSuper(Integer id) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public List<Sighting> getAllSightingsForLocation(Integer id) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public Sighting addSighting(Sighting toAdd) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public void deleteSighting(Integer id) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

    @Override
    public Sighting editSighting(Sighting sighting) throws SightingPersistenceException {
        throw new SightingPersistenceException(null);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.LocationDao;
import com.sg.deku.daos.LocationPersistenceException;
import com.sg.deku.models.Location;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class AlwaysFailLocationDao implements LocationDao {

    @Override
    public Location addLocation(Location toAdd) throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

    @Override
    public List<Location> getAllLocations() throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

    @Override
    public Location getLocationByID(Integer id) throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

    @Override
    public Location getLocationForSighting(Integer id) throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

    @Override
    public void deleteLocation(Integer id) throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

    @Override
    public Location editLocation(Location location) throws LocationPersistenceException {
        throw new LocationPersistenceException(null);
    }

}

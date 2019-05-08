/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daos;

import com.sg.deku.models.Location;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface LocationDao {

    public Location addLocation(Location toAdd) throws LocationPersistenceException;

    public List<Location> getAllLocations() throws LocationPersistenceException;

    public Location getLocationByID(Integer id) throws LocationPersistenceException;

    public Location getLocationForSighting(Integer id) throws LocationPersistenceException;

    public void deleteLocation(Integer id) throws LocationPersistenceException;

    public Location editLocation(Location location) throws LocationPersistenceException;
    
}

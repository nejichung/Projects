/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.LocationDao;
import com.sg.deku.daos.LocationPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryLocationDao implements LocationDao {

    List<Location> allLocations = new ArrayList<>();

    public InMemoryLocationDao() {
        Location konoha = new Location();
        konoha.setLocationID(1);
        konoha.setLocationName("Konoha");
        konoha.setDescription("Pain");
        konoha.setAddress("123 Hidden Leaf Village");
        konoha.setLatitude(23.5);
        konoha.setLongitude(40.7);
        allLocations.add(konoha);

        Location soul = new Location();
        soul.setLocationID(2);
        soul.setLocationName("Soul Society");
        soul.setDescription("Death");
        soul.setAddress("834 The Afterlife");
        soul.setLatitude(12.1);
        soul.setLongitude(63.4);
        allLocations.add(soul);

        Location badName = new Location();
        badName.setLocationID(3);
        badName.setLocationName("");
        badName.setDescription("Death");
        badName.setAddress("834 The Afterlife");
        badName.setLatitude(12.1);
        badName.setLongitude(63.4);
        allLocations.add(badName);

        Location badDescription = new Location();
        badDescription.setLocationID(4);
        badDescription.setLocationName("Soul Society");
        badDescription.setDescription("");
        badDescription.setAddress("834 The Afterlife");
        badDescription.setLatitude(12.1);
        badDescription.setLongitude(63.4);
        allLocations.add(badDescription);

        Location badAddress = new Location();
        badAddress.setLocationID(5);
        badAddress.setLocationName("Soul Society");
        badAddress.setDescription("Death");
        badAddress.setAddress("");
        badAddress.setLatitude(12.1);
        badAddress.setLongitude(63.4);
        allLocations.add(badAddress);

        Location badLatitude = new Location();
        badLatitude.setLocationID(2);
        badLatitude.setLocationName("Soul Society");
        badLatitude.setDescription("Death");
        badLatitude.setAddress("834 The Afterlife");
        badLatitude.setLatitude(90.1);
        badLatitude.setLongitude(63.4);
        allLocations.add(badLatitude);

        Location badLongitude = new Location();
        badLongitude.setLocationID(2);
        badLongitude.setLocationName("Soul Society");
        badLongitude.setDescription("Death");
        badLongitude.setAddress("834 The Afterlife");
        badLongitude.setLatitude(12.1);
        badLongitude.setLongitude(180.1);
        allLocations.add(badLongitude);

    }

    @Override
    public Location addLocation(Location toAdd) throws LocationPersistenceException {
        List<Location> allLocations = getAllLocations(); // might not be necessary since we built stop

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < allLocations.size(); i++) {
            Location toCheck = allLocations.get(i);
            if (toCheck.getLocationID() > maxNumber) {
                maxNumber = toCheck.getLocationID();
            }
        }
        toAdd.setLocationID(maxNumber + 1);
        return toAdd;
    }

    @Override
    public List<Location> getAllLocations() throws LocationPersistenceException {
        return allLocations;
    }

    @Override
    public Location getLocationByID(Integer id) throws LocationPersistenceException {
        Location toReturn = null;
        for (Location toCheck : allLocations) {
            if (toCheck.getLocationID() == id) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public Location getLocationForSighting(Integer id) throws LocationPersistenceException {
//        List<Location> freshLocations = new ArrayList<>();
//        for (Location toCheck : allLocations) {
//            for (Sighting sightingToCheck : toCheck.getAllSightings()) {
//                if (sightingToCheck.getSightingID() == id) {
//                    freshLocations.add(toCheck);
//                }
//            }
//        }
//        return freshLocations;
        Location toReturn = null;
        for (Location toCheck : allLocations) {
            for (Sighting sightingToCheck : toCheck.getAllSightings()) {
                if (sightingToCheck.getSightingID() == id) {
                    toReturn = toCheck;
                    break;
                }
            }
        }
        return toReturn;
    }

    @Override
    public void deleteLocation(Integer id) throws LocationPersistenceException {
          int index = Integer.MIN_VALUE;
        for (int i = 0; i < allLocations.size(); i++){
            Location toCheck = allLocations.get(i);
            if(toCheck.getLocationID() == id){
                index = i;
                allLocations.remove(index);
                break;
            }
        }
    }

    @Override
    public Location editLocation(Location location) throws LocationPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

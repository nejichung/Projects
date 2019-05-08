/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.SightingDao;
import com.sg.deku.daos.SightingPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemorySightingDao implements SightingDao { //ayy i think i did it

    List<Sighting> allSightings = new ArrayList<>();

    public InMemorySightingDao() {
        Sighting nuke = new Sighting();
        nuke.setSightingID(1);
        nuke.setDate(LocalDate.of(2010, 2, 2));
        nuke.setLocationID(1);
        allSightings.add(nuke);

        Sighting badDate = new Sighting();
        badDate.setSightingID(1);
        badDate.setDate(LocalDate.of(8030, 2, 2));
        badDate.setLocationID(1);
        allSightings.add(badDate);

        Sighting badLocationID = new Sighting();
        badLocationID.setSightingID(1);
        badLocationID.setDate(LocalDate.of(2010, 2, 2));
        badLocationID.setLocationID(0);
        allSightings.add(badLocationID);

    }

    @Override
    public List<Sighting> getAllSightings() throws SightingPersistenceException {
        return allSightings;
    }

    @Override
    public Sighting getSightingByID(Integer id) throws SightingPersistenceException {
        Sighting toReturn = null;
        for (Sighting toCheck : allSightings) {
            if (toCheck.getSightingID() == id) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public List<Sighting> getAllSightingsForSuper(Integer id) throws SightingPersistenceException {
        List<Sighting> freshSightings = new ArrayList<>();
        for (Sighting toCheck : allSightings) {
            for (Super superToCheck : toCheck.getAllSupers()) {
                if (superToCheck.getSuperID() == id) {
                    freshSightings.add(toCheck);
                }
            }
        }
        return freshSightings;
    }
    @Override // we have a location id, loop thru looking for match. then add to list? confused
    public List<Sighting> getAllSightingsForLocation(Integer id) throws SightingPersistenceException {
        // have a locaiton id, loop thru a list of loatiosn to get an object. from there we can get list of sighithgs
        List<Sighting> freshSightings = new ArrayList<>();
        for (Sighting toCheck : allSightings) {
            if (toCheck.getLocationID() == id) {
                freshSightings.add(toCheck);
            }
        }
        return freshSightings;
    }

    @Override
    public Sighting addSighting(Sighting toAdd) throws SightingPersistenceException {
//        List<Sighting> allSightings = getAllSightings(); // might not be necessary since we built stop

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < allSightings.size(); i++) {
            Sighting toCheck = allSightings.get(i);
            if (toCheck.getSightingID() > maxNumber) {
                maxNumber = toCheck.getSightingID();
            }
        }
        toAdd.setSightingID(maxNumber + 1);
        return toAdd;
    }

    @Override
    public void deleteSighting(Integer id) throws SightingPersistenceException {
          int index = Integer.MIN_VALUE;
        for (int i = 0; i < allSightings.size(); i++){
            Sighting toCheck = allSightings.get(i);
            if(toCheck.getSightingID() == id){
                index = i;
                allSightings.remove(index);
                break;
            }
        }
    }

    @Override
    public Sighting editSighting(Sighting sighting) throws SightingPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

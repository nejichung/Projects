/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.SuperDao;
import com.sg.deku.daos.SuperPersistenceException;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemorySuperDao implements SuperDao {

    List<Super> allSupers = new ArrayList<>();

    public InMemorySuperDao() {

        Super toshinori = new Super();
        toshinori.setSuperID(1);
        toshinori.setSuperName("All Might");
        toshinori.setDescription("#1 Hero");
        toshinori.setQuirk("One For All");
        allSupers.add(toshinori);

        Super badName = new Super();
        badName.setSuperID(1);
        badName.setSuperName("");
        badName.setDescription("#1 Hero");
        badName.setQuirk("One For All");
        allSupers.add(badName);

        Super badDescription = new Super();
        badDescription.setSuperID(1);
        badDescription.setSuperName("All Might");
        badDescription.setDescription("");
        badDescription.setQuirk("One For All");
        allSupers.add(badDescription);

        Super badQuirk = new Super();
        badQuirk.setSuperID(1);
        badQuirk.setSuperName("All Might");
        badQuirk.setDescription("#1 Hero");
        badQuirk.setQuirk("");
        allSupers.add(badQuirk);

    }

    @Override
    public List<Super> getAllSupers() throws SuperPersistenceException {
        return allSupers;
    }

    @Override
    public Super getSuperByID(Integer id) throws SuperPersistenceException {
        Super toReturn = null;
        for (Super toCheck : allSupers) {
            if (toCheck.getSuperID() == id) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public List<Super> getAllSupersForOrganization(Integer id) throws SuperPersistenceException {
        List<Super> freshSupers = new ArrayList<>();
        for (Super toCheck : allSupers) {
            for (Organization orgToCheck : toCheck.getAllOrganizations()) {
                if (orgToCheck.getOrganizationID() == id) {
                    freshSupers.add(toCheck);
                }
            }
        }
        return freshSupers;
    }

    @Override
    public List<Super> getAllSupersForSighting(Integer id) throws SuperPersistenceException {
        List<Super> freshSupers = new ArrayList<>();
        for (Super toCheck : allSupers) {
            for (Sighting sightingToCheck : toCheck.getAllSightings()) {
                if (sightingToCheck.getSightingID() == id) {
                    freshSupers.add(toCheck);
                }
            }
        }
        return freshSupers;
    }

    @Override
    public Super addSuper(Super toAdd) throws SuperPersistenceException {
        List<Super> allLocations = getAllSupers(); // might not be necessary since we built stop

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < allLocations.size(); i++) {
            Super toCheck = allLocations.get(i);
            if (toCheck.getSuperID() > maxNumber) {
                maxNumber = toCheck.getSuperID();
            }
        }
        toAdd.setSuperID(maxNumber + 1);
        return toAdd;
    }

    @Override
    public void deleteSuper(Integer id) throws SuperPersistenceException {
          int index = Integer.MIN_VALUE;
        for (int i = 0; i < allSupers.size(); i++){
            Super toCheck = allSupers.get(i);
            if(toCheck.getSuperID() == id){
                index = i;
                allSupers.remove(index);
                break;
            }
        }
    }

    @Override
    public Super editSuper(Super sup) throws SuperPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

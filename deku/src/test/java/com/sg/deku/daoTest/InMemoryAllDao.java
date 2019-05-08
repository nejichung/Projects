/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.LocationDao;
import com.sg.deku.daos.LocationPersistenceException;
import com.sg.deku.daos.OrganizationDao;
import com.sg.deku.daos.OrganizationPersistenceException;
import com.sg.deku.daos.SightingDao;
import com.sg.deku.daos.SightingPersistenceException;
import com.sg.deku.daos.SuperDao;
import com.sg.deku.daos.SuperPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryAllDao implements SuperDao, SightingDao, LocationDao, OrganizationDao {

    List<Location> allLocations = new ArrayList<>();
    List<Organization> allOrganizations = new ArrayList<>();
    List<Sighting> allSightings = new ArrayList<>();
    List<Super> allSupers = new ArrayList<>();

    public InMemoryAllDao() {
        

//        Location soul = new Location();
//        soul.setLocationID(2);
//        soul.setLocationName("Soul Society");
//        soul.setDescription("Death");
//        soul.setAddress("834 The Afterlife");
//        soul.setLatitude(12.1);
//        soul.setLongitude(63.4);
//        allLocations.add(soul);
//
//        Location badName = new Location();
//        badName.setLocationID(3);
//        badName.setLocationName("");
//        badName.setDescription("Death");
//        badName.setAddress("834 The Afterlife");
//        badName.setLatitude(12.1);
//        badName.setLongitude(63.4);
//        allLocations.add(badName);
//
//        Location badDescription = new Location();
//        badDescription.setLocationID(4);
//        badDescription.setLocationName("Soul Society");
//        badDescription.setDescription("");
//        badDescription.setAddress("834 The Afterlife");
//        badDescription.setLatitude(12.1);
//        badDescription.setLongitude(63.4);
//        allLocations.add(badDescription);
//
//        Location badAddress = new Location();
//        badAddress.setLocationID(5);
//        badAddress.setLocationName("Soul Society");
//        badAddress.setDescription("Death");
//        badAddress.setAddress("");
//        badAddress.setLatitude(12.1);
//        badAddress.setLongitude(63.4);
//        allLocations.add(badAddress);
//
//        Location badLatitude = new Location();
//        badLatitude.setLocationID(6);
//        badLatitude.setLocationName("Soul Society");
//        badLatitude.setDescription("Death");
//        badLatitude.setAddress("834 The Afterlife");
//        badLatitude.setLatitude(90.1);
//        badLatitude.setLongitude(63.4);
//        allLocations.add(badLatitude);
//
//        Location badLongitude = new Location();
//        badLongitude.setLocationID(7);
//        badLongitude.setLocationName("Soul Society");
//        badLongitude.setDescription("Death");
//        badLongitude.setAddress("834 The Afterlife");
//        badLongitude.setLatitude(12.1);
//        badLongitude.setLongitude(180.1);
//        allLocations.add(badLongitude);

        Organization akatsuki = new Organization();
        akatsuki.setOrganizationID(1);
        akatsuki.setOrganizationName("Akatsuki");
        akatsuki.setDescription("Infinite Tsukuyomi");
        akatsuki.setAddress("That one cave");
        akatsuki.setPhoneNumber("8901234569");
        akatsuki.setEmailAddress("knowtruepain@rain.com");
        akatsuki.setSuperID(1);
        allOrganizations.add(akatsuki);

//        Organization badName2 = new Organization();
//        badName2.setOrganizationID(2);
//        badName2.setOrganizationName("");
//        badName2.setDescription("Infinite Tsukiyomi");
//        badName2.setAddress("That one cave");
//        badName2.setPhoneNumber("8901234569");
//        badName2.setEmailAddress("knowtruepain@rain.com");
//        badName2.setSuperID(1);
//        allOrganizations.add(badName2);
//
//        Organization badDescription2 = new Organization();
//        badDescription2.setOrganizationID(3);
//        badDescription2.setOrganizationName("Akatsuki");
//        badDescription2.setDescription("");
//        badDescription2.setAddress("That one cave");
//        badDescription2.setPhoneNumber("8901234569");
//        badDescription2.setEmailAddress("knowtruepain@rain.com");
//        
//        allOrganizations.add(badDescription2);
//
//        Organization badAddress2 = new Organization();
//        badAddress2.setOrganizationID(4);
//        badAddress2.setOrganizationName("Akatsuki");
//        badAddress2.setDescription("Infinite Tsukiyomi");
//        badAddress2.setAddress("");
//        badAddress2.setPhoneNumber("8901234569");
//        badAddress2.setEmailAddress("knowtruepain@rain.com");
//        allOrganizations.add(badAddress2);
//
//        Organization badPhoneNumber = new Organization();
//        badPhoneNumber.setOrganizationID(5);
//        badPhoneNumber.setOrganizationName("Akatsuki");
//        badPhoneNumber.setDescription("Infinite Tsukiyomi");
//        badPhoneNumber.setAddress("That one cave");
//        badPhoneNumber.setPhoneNumber("");
//        badPhoneNumber.setEmailAddress("knowtruepain@rain.com");
//        allOrganizations.add(badPhoneNumber);
//
//        Organization badEmailAddress = new Organization();
//        badEmailAddress.setOrganizationID(6);
//        badEmailAddress.setOrganizationName("Akatsuki");
//        badEmailAddress.setDescription("Infinite Tsukiyomi");
//        badEmailAddress.setAddress("That one cave");
//        badEmailAddress.setPhoneNumber("8901234569");
//        badEmailAddress.setEmailAddress("knowtruepain@rain.com");
//        allOrganizations.add(badEmailAddress);
        
        Super goku = new Super();
        goku.setSuperID(2);
        goku.setSuperName("goku");
        goku.setDescription("hungry");
        goku.setQuirk("op");
        

        Sighting nuke = new Sighting();
        nuke.setSightingID(1);
        nuke.setDate(LocalDate.of(2010, 2, 2));
        nuke.setLocationID(1);
        nuke.setAllSupers(allSupers);
        allSightings.add(nuke);
        
        Location konoha = new Location();
        konoha.setLocationID(1);
        konoha.setLocationName("Konoha");
        konoha.setDescription("Pain");
        konoha.setAddress("123 Hidden Leaf Village");
        konoha.setLatitude(23.5);
        konoha.setLongitude(40.7);
        
        List<Sighting> sigForLoc = new ArrayList<>();
        sigForLoc.add(nuke);
        konoha.setAllSightings(sigForLoc);
        allLocations.add(konoha);

//        Sighting badDate = new Sighting();
//        badDate.setSightingID(2);
//        badDate.setDate(LocalDate.of(8030, 2, 2));
//        badDate.setLocationID(1);
//        allSightings.add(badDate);
//
//        Sighting badLocationID = new Sighting();
//        badLocationID.setSightingID(3);
//        badLocationID.setDate(LocalDate.of(2010, 2, 2));
//        badLocationID.setLocationID(0);
//        allSightings.add(badLocationID);
        
        Super toshinori = new Super();
        toshinori.setSuperID(1);
        toshinori.setSuperName("All Might");
        toshinori.setDescription("#1 Hero");
        toshinori.setQuirk("One For All");
        
        List<Sighting> sigForSuper = new ArrayList<>();
        sigForSuper.add(nuke);
        toshinori.setAllSightings(sigForSuper);
        
        List<Organization> orgForSuper = new ArrayList<>();
        orgForSuper.add(akatsuki);
        toshinori.setAllOrganizations(orgForSuper);
        allSupers.add(toshinori);

        
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
        allLocations.add(toAdd);
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
        int indexToReplace = Integer.MIN_VALUE;
        
        for( int i = 0; i < allLocations.size(); i++){
            Location toCheck = allLocations.get(i);
            
            if(toCheck.getLocationID() == location.getLocationID()){
                indexToReplace = i;
                break;
            }
        }
        allLocations.set(indexToReplace, location);
        
        return location;
    }
    
    @Override
    public List<Organization> getAllOrganizations() throws OrganizationPersistenceException {
        return allOrganizations;
    }

    @Override
    public Organization getOrganizationByID(Integer id) throws OrganizationPersistenceException {
        Organization toReturn = null;
        for (Organization toCheck : allOrganizations) {
            if (toCheck.getOrganizationID() == id) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public List<Organization> getAllOrganizationsForSuper(Integer id) throws OrganizationPersistenceException {
        List <Organization> freshOrganizations = new ArrayList<>();
        for (Organization toCheck : allOrganizations){
            if(toCheck.getSuperID() == id){
                freshOrganizations.add(toCheck);
            }
        }
              return freshOrganizations;
    }

    @Override
    public Organization addOrganization(Organization toAdd) throws OrganizationPersistenceException {
        List<Organization> allOrganizations = getAllOrganizations(); // might not be necessary since we built stop

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < allOrganizations.size(); i++) {
            Organization toCheck = allOrganizations.get(i);
            if (toCheck.getOrganizationID() > maxNumber) {
                maxNumber = toCheck.getOrganizationID();
            }
        }
        toAdd.setOrganizationID(maxNumber + 1);
        allOrganizations.add(toAdd);
        return toAdd;
    }

    @Override
    public Organization editOrg(Organization org) throws OrganizationPersistenceException {
        int indexToReplace = Integer.MIN_VALUE;
        
        for( int i = 0; i < allOrganizations.size(); i++){
            Organization toCheck = allOrganizations.get(i);
            
            if(toCheck.getOrganizationID() == org.getOrganizationID()){
                indexToReplace = i;
                break;
            }
        }
        allOrganizations.set(indexToReplace, org);
        
        return org;
    }

    @Override
    public void deleteOrganization(Integer id) throws OrganizationPersistenceException {
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < allOrganizations.size(); i++){
            Organization toCheck = allOrganizations.get(i);
            if(toCheck.getOrganizationID() == id){
                index = i;
                allOrganizations.remove(index);
                break;
            }
        }
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
        allSightings.add(toAdd);
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
        int indexToReplace = Integer.MIN_VALUE;
        
        for( int i = 0; i < allSightings.size(); i++){
            Sighting toCheck = allSightings.get(i);
            
            if(toCheck.getSightingID() == sighting.getSightingID()){
                indexToReplace = i;
                break;
            }
        }
        allSightings.set(indexToReplace, sighting);
        
        return sighting;
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
        for (Super toCheck : allSupers) {  // super 2 3 4 have null allSightings which is weird since i set them
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
        List<Super> allSupers = getAllSupers(); // might not be necessary since we built stop

        int maxNumber = Integer.MIN_VALUE;

        for (int i = 0; i < allSupers.size(); i++) {
            Super toCheck = allSupers.get(i);
            if (toCheck.getSuperID() > maxNumber) {
                maxNumber = toCheck.getSuperID();
            }
        }
        toAdd.setSuperID(maxNumber + 1);
        allSupers.add(toAdd);
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
        int indexToReplace = Integer.MIN_VALUE;
        
        for( int i = 0; i < allSupers.size(); i++){
            Super toCheck = allSupers.get(i);
            
            if(toCheck.getSuperID() == sup.getSuperID()){
                indexToReplace = i;
                break;
            }
        }
        allSupers.set(indexToReplace, sup);
        
        return sup;
    }

}

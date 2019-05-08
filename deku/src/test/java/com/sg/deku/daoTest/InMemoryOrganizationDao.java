/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.sg.deku.daos.OrganizationDao;
import com.sg.deku.daos.OrganizationPersistenceException;
import com.sg.deku.models.Organization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryOrganizationDao implements OrganizationDao { //good?

    List<Organization> allOrganizations = new ArrayList<>();

    public InMemoryOrganizationDao() {
        Organization akatsuki = new Organization();
        akatsuki.setOrganizationID(1);
        akatsuki.setOrganizationName("Akatsuki");
        akatsuki.setDescription("Infinite Tsukiyomi");
        akatsuki.setAddress("That one cave");
        akatsuki.setPhoneNumber("8901234569");
        akatsuki.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(akatsuki);

        Organization badName = new Organization();
        badName.setOrganizationID(1);
        badName.setOrganizationName("");
        badName.setDescription("Infinite Tsukiyomi");
        badName.setAddress("That one cave");
        badName.setPhoneNumber("8901234569");
        badName.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(badName);

        Organization badDescription = new Organization();
        badDescription.setOrganizationID(1);
        badDescription.setOrganizationName("Akatsuki");
        badDescription.setDescription("");
        badDescription.setAddress("That one cave");
        badDescription.setPhoneNumber("8901234569");
        badDescription.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(badDescription);

        Organization badAddress = new Organization();
        badAddress.setOrganizationID(1);
        badAddress.setOrganizationName("Akatsuki");
        badAddress.setDescription("Infinite Tsukiyomi");
        badAddress.setAddress("");
        badAddress.setPhoneNumber("8901234569");
        badAddress.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(badAddress);

        Organization badPhoneNumber = new Organization();
        badPhoneNumber.setOrganizationID(1);
        badPhoneNumber.setOrganizationName("Akatsuki");
        badPhoneNumber.setDescription("Infinite Tsukiyomi");
        badPhoneNumber.setAddress("That one cave");
        badPhoneNumber.setPhoneNumber("");
        badPhoneNumber.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(badPhoneNumber);

        Organization badEmailAddress = new Organization();
        badEmailAddress.setOrganizationID(1);
        badEmailAddress.setOrganizationName("Akatsuki");
        badEmailAddress.setDescription("Infinite Tsukiyomi");
        badEmailAddress.setAddress("That one cave");
        badEmailAddress.setPhoneNumber("8901234569");
        badEmailAddress.setEmailAddress("knowtruepain@rain.com");
        allOrganizations.add(badEmailAddress);
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

}

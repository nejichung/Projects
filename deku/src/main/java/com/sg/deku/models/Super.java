/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Jacob
 */
public class Super {
    
   private Integer superID;
   
   @Size(max = 30, message = "Name must be less than 30 characters.")
   @NotBlank (message = "Name must not be blank")
   private String superName;
   
   @Size(max = 50, message = "Description must be less than 50 characters.")
   @NotBlank (message = "Description must not be blank")
   private String description;
   
   @Size(max = 40, message = "Quirk must be less than 40 characters.")
   @NotBlank (message = "Quirk must not be blank")
   private String quirk;
   //private Location singleLocation; // 1 location per sighting. so only one location
   private List<Sighting> allSightings;
   private List<Organization> allOrganizations;

   public boolean hasOrg(int organizationID){
       boolean found = false;
       for(Organization toCheck : allOrganizations){
           if(toCheck.getOrganizationID() == organizationID){
               found = true;
               break;
           }
       }
       return found;
   }
   
   public boolean hasSighting(int sightingID){
       boolean found = false;
       for(Sighting toCheck : allSightings){
           if(toCheck.getSightingID() == sightingID){
               found = true;
               break;
           }
       }
       return found;
   }
   
    public Integer getSuperID() {
        return superID;
    }

    /**
     * @param superID the superID to set
     */
    public void setSuperID(Integer superID) {
        this.superID = superID;
    }

    /**
     * @return the superName
     */
    public String getSuperName() {
        return superName;
    }

    /**
     * @param superName the superName to set
     */
    public void setSuperName(String superName) {
        this.superName = superName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the quirk
     */
    public String getQuirk() {
        return quirk;
    }

    /**
     * @param quirk the quirk to set
     */
    public void setQuirk(String quirk) {
        this.quirk = quirk;
    }

    /**
     * @return the singleLocation
     */
//    public Location getSingleLocation() {
//        return singleLocation;
//    }
//
//    /**
//     * @param singleLocation the singleLocation to set
//     */
//    public void setSingleLocation(Location singleLocation) {
//        this.singleLocation = singleLocation;
//    }

    /**
     * @return the allSightings
     */
    public List<Sighting> getAllSightings() {
        return allSightings;
    }

    /**
     * @param allSightings the allSightings to set
     */
    public void setAllSightings(List<Sighting> allSightings) {
        this.allSightings = allSightings;
    }

//    /**
//     * @return the singleOrganization
//     */
//    public Organization getSingleOrganization() {
//        return singleOrganization;
//    }
//
//    /**
//     * @param singleOrganization the singleOrganization to set
//     */
//    public void setSingleOrganization(Organization singleOrganization) {
//        this.singleOrganization = singleOrganization;
//    }

    /**
     * @return the allOrganizations
     */
    public List<Organization> getAllOrganizations() {
        return allOrganizations;
    }

    /**
     * @param allOrganizations the allOrganizations to set
     */
    public void setAllOrganizations(List<Organization> allOrganizations) {
        this.allOrganizations = allOrganizations;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;


import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Jacob
 */
public class Sighting {

    /**
     * @return the allSupers
     */
    public List<Super> getAllSupers() {
        return allSupers;
    }

    /**
     * @param allSupers the allSupers to set
     */
    public void setAllSupers(List<Super> allSupers) {
        this.allSupers = allSupers;
    }
        
    private Integer sightingID;
    
    @Past (message = "Date must not be in the future.")
    @NotNull(message = "Date must not be blank.")  
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate date;
    
    @NotEmpty (message = "You have to select at least one super for a sighting")
    private List<Super> allSupers;

    private Integer locationID;    
    private Location singleLocation;

    public boolean hasSup(int superID){
        boolean found = false;
        for(Super toCheck : allSupers){
            if( toCheck.getSuperID() == superID){
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * @return the sightingID
     */
    public Integer getSightingID() {
        return sightingID;
    }

    /**
     * @param sightingID the sightingID to set
     */
    public void setSightingID(Integer sightingID) {
        this.sightingID = sightingID;
    }
    
    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the singleLocation
     */
    public Location getSingleLocation() {
        return singleLocation;
    }

    /**
     * @param singleLocation the singleLocation to set
     */
    public void setSingleLocation(Location singleLocation) {
        this.singleLocation = singleLocation;
    }

    /**
     * @return the locationID
     */
    public Integer getLocationID() {
        return locationID;
    }

    /**
     * @param locationID the locationID to set
     */
    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jacob
 */
public class Location {
    
    private Integer locationID;
    
    @Size(max = 30, message = "Name must be less than 30 characters.")
    @NotBlank (message = "Name must not be blank")
    private String locationName;
    
    @Size(max = 50, message = "Description must be less than 50 characters.")
    @NotBlank (message = "Description must not be blank")
    private String description;
    
    @Size(max = 60, message = "Address must be less than 60 characters.")
    @NotBlank(message = "Address must not be blank")
    private String address;
    
    @NotNull (message = "Latitude must not be blank")
    private Double latitude; // need ranges for lat and long adk david
    
    @NotNull (message = "Longitude must not be blank")
    private Double longitude;
    
    private List<Sighting> allSightings;
    
    
    public boolean hasSighting(int sightingID){
        boolean found = false;
        for(Sighting toCheck : allSightings){
            if( toCheck.getSightingID() == sightingID){
                found = true;
                break;
            }
        }
        return found;
        
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
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

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
}

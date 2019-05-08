/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;

import java.util.List;

/**
 *
 * @author Software Guld
 */
public class EditLocationViewModel {
    private List<Sighting> allSightings;
    private Location location;
    private int[] selectedSightingIDs;

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

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the selectedSightingIDs
     */
    public int[] getSelectedSightingIDs() {
        return selectedSightingIDs;
    }

    /**
     * @param selectedSightingIDs the selectedSightingIDs to set
     */
    public void setSelectedSightingIDs(int[] selectedSightingIDs) {
        this.selectedSightingIDs = selectedSightingIDs;
    }
}

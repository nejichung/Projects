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
public class EditSightingViewModel {
    private Sighting sighting;
    private int[] selectedIDs;
    private List<Super> allSupers;
    private List<Location> allLocations;
    
    /**
     * @return the sighting
     */
    public Sighting getSighting() {
        return sighting;
    }

    /**
     * @param sighting the sighting to set
     */
    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    /**
     * @return the selectedIDs
     */
    public int[] getSelectedIDs() {
        return selectedIDs;
    }

    /**
     * @param selectedIDs the selectedIDs to set
     */
    public void setSelectedIDs(int[] selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

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

    /**
     * @return the allLocations
     */
    public List<Location> getAllLocations() {
        return allLocations;
    }

    /**
     * @param allLocations the allLocations to set
     */
    public void setAllLocations(List<Location> allLocations) {
        this.allLocations = allLocations;
    }
}

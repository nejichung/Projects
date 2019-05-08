/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.models;

import java.util.List;

/**
 *
 * @author Jacob
 */
public class AddSightingViewModel {
    private List<Super> allSupers;
    private int[] selectedSuperIDs;
    private Sighting sighting;

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
     * @return the selectedSuperIDs
     */
    public int[] getSelectedSuperIDs() {
        return selectedSuperIDs;
    }

    /**
     * @param selectedSuperIDs the selectedSuperIDs to set
     */
    public void setSelectedSuperIDs(int[] selectedSuperIDs) {
        this.selectedSuperIDs = selectedSuperIDs;
    }

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
}

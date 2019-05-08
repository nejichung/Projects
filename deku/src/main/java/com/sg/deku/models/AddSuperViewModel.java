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
public class AddSuperViewModel {
    private List<Organization> allOrganizations;
    private int[] selectedOrganizationIDs;
    private Super sup;
    private List<Sighting> allSightings;
    private int[] selectedSightingIDs;

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

    /**
     * @return the selectedOrganizationIDs
     */
    public int[] getSelectedOrganizationIDs() {
        return selectedOrganizationIDs;
    }

    /**
     * @param selectedOrganizationIDs the selectedOrganizationIDs to set
     */
    public void setSelectedOrganizationIDs(int[] selectedOrganizationIDs) {
        this.selectedOrganizationIDs = selectedOrganizationIDs;
    }

    /**
     * @return the sup
     */
    public Super getSup() {
        return sup;
    }

    /**
     * @param sup the sup to set
     */
    public void setSup(Super sup) {
        this.sup = sup;
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

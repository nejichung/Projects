/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Organization;

/**
 *
 * @author Jacob
 */
public class AddOrganizationResponse extends Response {
    private Organization addedOrganization;

    /**
     * @return the addedOrganization
     */
    public Organization getAddedOrganization() {
        return addedOrganization;
    }

    /**
     * @param addedOrganization the addedOrganization to set
     */
    public void setAddedOrganization(Organization addedOrganization) {
        this.addedOrganization = addedOrganization;
    }
}

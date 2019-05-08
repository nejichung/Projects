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
public class GetOrganizationByIDResponse extends Response {
    private Organization singleOrganization;

    /**
     * @return the singleOrganization
     */
    public Organization getSingleOrganization() {
        return singleOrganization;
    }

    /**
     * @param singleOrganization the singleOrganization to set
     */
    public void setSingleOrganization(Organization singleOrganization) {
        this.singleOrganization = singleOrganization;
    }
}

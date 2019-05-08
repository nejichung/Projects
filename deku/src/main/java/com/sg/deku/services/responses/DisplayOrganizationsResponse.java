/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Organization;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class DisplayOrganizationsResponse extends Response {
    private List<Organization> allOrganizations;

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

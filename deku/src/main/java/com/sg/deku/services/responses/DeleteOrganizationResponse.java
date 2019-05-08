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
public class DeleteOrganizationResponse extends Response{
    private Organization deletedOrg;

    /**
     * @return the deletedOrg
     */
    public Organization getDeletedOrg() {
        return deletedOrg;
    }

    /**
     * @param deletedOrg the deletedOrg to set
     */
    public void setDeletedOrg(Organization deletedOrg) {
        this.deletedOrg = deletedOrg;
    }
}

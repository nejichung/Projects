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
public class EditOrganizationResponse extends Response{
    private Organization editedOrg;

    /**
     * @return the editedOrg
     */
    public Organization getEditedOrg() {
        return editedOrg;
    }

    /**
     * @param editedOrg the editedOrg to set
     */
    public void setEditedOrg(Organization editedOrg) {
        this.editedOrg = editedOrg;
    }
    
}

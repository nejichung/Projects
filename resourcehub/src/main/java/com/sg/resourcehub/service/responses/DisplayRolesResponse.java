/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Role;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class DisplayRolesResponse extends Response {
    private List<Role> allRoles;

    /**
     * @return the allRoles
     */
    public List<Role> getAllRoles() {
        return allRoles;
    }

    /**
     * @param allRoles the allRoles to set
     */
    public void setAllRoles(List<Role> allRoles) {
        this.allRoles = allRoles;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Role;

/**
 *
 * @author Software Guld
 */
public class GetRoleByIdResponse extends Response {
    private Role singleRole;

    /**
     * @return the singleRole
     */
    public Role getSingleRole() {
        return singleRole;
    }

    /**
     * @param singleRole the singleRole to set
     */
    public void setSingleRole(Role singleRole) {
        this.singleRole = singleRole;
    }
}

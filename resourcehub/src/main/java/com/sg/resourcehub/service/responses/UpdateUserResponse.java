/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.User;

/**
 *
 * @author Software Guld
 */
public class UpdateUserResponse extends Response {
    private User updatedUser;

    /**
     * @return the updatedUser
     */
    public User getUpdatedUser() {
        return updatedUser;
    }

    /**
     * @param updatedUser the updatedUser to set
     */
    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }
}

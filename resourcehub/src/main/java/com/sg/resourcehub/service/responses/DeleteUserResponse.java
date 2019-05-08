/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.User;

/**
 *
 * @author Software Guld
 */
public class DeleteUserResponse extends Response{
    private User deletedUser;

    /**
     * @return the deletedUser
     */
    public User getDeletedUser() {
        return deletedUser;
    }

    /**
     * @param deletedUser the deletedUser to set
     */
    public void setDeletedUser(User deletedUser) {
        this.deletedUser = deletedUser;
    }
}

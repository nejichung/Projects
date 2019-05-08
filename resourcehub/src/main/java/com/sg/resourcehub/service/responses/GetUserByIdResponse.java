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
public class GetUserByIdResponse extends Response{
    private User singleUser; 

    /**
     * @return the singleUser
     */
    public User getSingleUser() {
        return singleUser;
    }

    /**
     * @param singleUser the singleUser to set
     */
    public void setSingleUser(User singleUser) {
        this.singleUser = singleUser;
    }
}

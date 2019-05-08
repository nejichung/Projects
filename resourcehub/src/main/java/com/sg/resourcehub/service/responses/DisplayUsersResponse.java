/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.User;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class DisplayUsersResponse extends Response {
    private List<User> allUsers;

    /**
     * @return the allUsers
     */
    public List<User> getAllUsers() {
        return allUsers;
    }

    /**
     * @param allUsers the allUsers to set
     */
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}

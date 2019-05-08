/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Requester;

/**
 *
 * @author Software Guld
 */
public class GetRequesterByIDResponse extends Response{
    private Requester singleRequester;

    /**
     * @return the singleRequester
     */
    public Requester getSingleRequester() {
        return singleRequester;
    }

    /**
     * @param singleRequester the singleRequester to set
     */
    public void setSingleRequester(Requester singleRequester) {
        this.singleRequester = singleRequester;
    }
}

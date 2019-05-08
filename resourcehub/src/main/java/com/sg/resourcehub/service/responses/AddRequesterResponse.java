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
public class AddRequesterResponse extends Response {
    private Requester addedRequester;

    /**
     * @return the addedRequester
     */
    public Requester getAddedRequester() {
        return addedRequester;
    }

    /**
     * @param addedRequester the addedRequester to set
     */
    public void setAddedRequester(Requester addedRequester) {
        this.addedRequester = addedRequester;
    }
    
}

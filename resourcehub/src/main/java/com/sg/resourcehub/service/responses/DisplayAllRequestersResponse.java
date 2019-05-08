/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Requester;
import java.util.List;

/**
 *
 * @author abdulmalik
 */
public class DisplayAllRequestersResponse extends Response{
    private List<Requester> allRequesters;

    /**
     * @return the allRequesters
     */
    public List<Requester> getAllRequesters() {
        return allRequesters;
    }

    /**
     * @param allRequesters the allRequesters to set
     */
    public void setAllRequesters(List<Requester> allRequesters) {
        this.allRequesters = allRequesters;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Sighting;

/**
 *
 * @author Software Guld
 */
public class GetSightingByIDResponse extends Response {
    private Sighting singleSighting;

    /**
     * @return the singleSighting
     */
    public Sighting getSingleSighting() {
        return singleSighting;
    }

    /**
     * @param singleSighting the singleSighting to set
     */
    public void setSingleSighting(Sighting singleSighting) {
        this.singleSighting = singleSighting;
    }
    
}

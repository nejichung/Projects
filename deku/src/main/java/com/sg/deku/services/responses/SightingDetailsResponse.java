/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.services.responses.Response;
import com.sg.deku.models.Sighting;

/**
 *
 * @author Software Guld
 */
public class SightingDetailsResponse extends Response {
    private Sighting sighting;

    /**
     * @return the sighting
     */
    public Sighting getSighting() {
        return sighting;
    }

    /**
     * @param sighting the sighting to set
     */
    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }
}

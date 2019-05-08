/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Sighting;

/**
 *
 * @author Jacob
 */
public class AddSightingResponse extends Response{
    private Sighting addedSighting;

    /**
     * @return the addedSighting
     */
    public Sighting getAddedSighting() {
        return addedSighting;
    }

    /**
     * @param addedSighting the addedSighting to set
     */
    public void setAddedSighting(Sighting addedSighting) {
        this.addedSighting = addedSighting;
    }
}

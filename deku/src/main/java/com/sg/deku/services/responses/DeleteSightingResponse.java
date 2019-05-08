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
public class DeleteSightingResponse extends Response{
    private Sighting deletedSighting;

    /**
     * @return the deletedSighting
     */
    public Sighting getDeletedSighting() {
        return deletedSighting;
    }

    /**
     * @param deletedSighting the deletedSighting to set
     */
    public void setDeletedSighting(Sighting deletedSighting) {
        this.deletedSighting = deletedSighting;
    }
}

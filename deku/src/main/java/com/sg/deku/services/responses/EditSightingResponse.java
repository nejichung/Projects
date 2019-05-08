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
public class EditSightingResponse extends Response {
    private Sighting editedSighting;

    /**
     * @return the editedSighting
     */
    public Sighting getEditedSighting() {
        return editedSighting;
    }

    /**
     * @param editedSighting the editedSighting to set
     */
    public void setEditedSighting(Sighting editedSighting) {
        this.editedSighting = editedSighting;
    }
}

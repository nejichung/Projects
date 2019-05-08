/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Location;

/**
 *
 * @author Software Guld
 */
public class AddLocationResponse extends Response {
    private Location addedLocation;

    /**
     * @return the addedLocation
     */
    public Location getAddedLocation() {
        return addedLocation;
    }

    /**
     * @param addedLocation the addedLocation to set
     */
    public void setAddedLocation(Location addedLocation) {
        this.addedLocation = addedLocation;
    }
}

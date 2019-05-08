/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Location;

/**
 *
 * @author Jacob
 */
public class DeleteLocationResponse extends Response{
    private Location deletedLocation;

    /**
     * @return the deletedLocation
     */
    public Location getDeletedLocation() {
        return deletedLocation;
    }

    /**
     * @param deletedLocation the deletedLocation to set
     */
    public void setDeletedLocation(Location deletedLocation) {
        this.deletedLocation = deletedLocation;
    }
}

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
public class GetLocationByIDResponse extends Response {
    private Location singleLocation;

    /**
     * @return the singleLocation
     */
    public Location getSingleLocation() {
        return singleLocation;
    }

    /**
     * @param singleLocation the singleLocation to set
     */
    public void setSingleLocation(Location singleLocation) {
        this.singleLocation = singleLocation;
    }
}

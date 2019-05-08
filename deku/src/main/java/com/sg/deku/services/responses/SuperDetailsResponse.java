/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.services.responses.Response;
import com.sg.deku.models.Super;

/**
 *
 * @author Software Guld
 */
public class SuperDetailsResponse extends Response {
    private Super singleSuper;

    /**
     * @return the singleSuper
     */
    public Super getSingleSuper() {
        return singleSuper;
    }

    /**
     * @param singleSuper the singleSuper to set
     */
    public void setSingleSuper(Super singleSuper) {
        this.singleSuper = singleSuper;
    }
}

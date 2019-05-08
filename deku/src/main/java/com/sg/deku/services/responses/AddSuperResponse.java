/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.responses;

import com.sg.deku.models.Super;

/**
 *
 * @author Software Guld
 */
public class AddSuperResponse extends Response {
    private Super addedSuper;

    /**
     * @return the addedSuper
     */
    public Super getAddedSuper() {
        return addedSuper;
    }

    /**
     * @param addedSuper the addedSuper to set
     */
    public void setAddedSuper(Super addedSuper) {
        this.addedSuper = addedSuper;
    }
}

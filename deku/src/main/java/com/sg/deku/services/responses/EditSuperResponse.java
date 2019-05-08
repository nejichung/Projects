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
public class EditSuperResponse extends Response {
    private Super editedSuper;

    /**
     * @return the editedSuper
     */
    public Super getEditedSuper() {
        return editedSuper;
    }

    /**
     * @param editedSuper the editedSuper to set
     */
    public void setEditedSuper(Super editedSuper) {
        this.editedSuper = editedSuper;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Supplier;

/**
 *
 * @author Software Guld
 */
public class EditSupplierResponse extends Response{
    private Supplier editedSupplier;

    /**
     * @return the editedSupplier
     */
    public Supplier getEditedSupplier() {
        return editedSupplier;
    }

    /**
     * @param editedSupplier the editedSupplier to set
     */
    public void setEditedSupplier(Supplier editedSupplier) {
        this.editedSupplier = editedSupplier;
    }
}

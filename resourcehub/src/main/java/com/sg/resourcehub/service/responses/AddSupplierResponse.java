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
public class AddSupplierResponse extends Response {
    private Supplier addedSupplier;

    /**
     * @return the addedSupplier
     */
    public Supplier getAddedSupplier() {
        return addedSupplier;
    }

    /**
     * @param addedSupplier the addedSupplier to set
     */
    public void setAddedSupplier(Supplier addedSupplier) {
        this.addedSupplier = addedSupplier;
    }
}

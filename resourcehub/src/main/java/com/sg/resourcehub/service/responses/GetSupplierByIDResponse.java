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
public class GetSupplierByIDResponse extends Response{
    private Supplier singleSupplier;

    /**
     * @return the singleSupplier
     */
    public Supplier getSingleSupplier() {
        return singleSupplier;
    }

    /**
     * @param singleSupplier the singleSupplier to set
     */
    public void setSingleSupplier(Supplier singleSupplier) {
        this.singleSupplier = singleSupplier;
    }
}

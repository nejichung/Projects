/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Supplier;
import java.util.List;

/**
 *
 * @author abdulmalik
 */
public class DisplayAllSuppliersResponse extends Response{
     private List<Supplier> allSuppliers;

    /**
     * @return the allSuppliers
     */
    public List<Supplier> getAllSuppliers() {
        return allSuppliers;
    }

    /**
     * @param allSuppliers the allSuppliers to set
     */
    public void setAllSuppliers(List<Supplier> allSuppliers) {
        this.allSuppliers = allSuppliers;
    }
}

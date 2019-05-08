/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Item;

/**
 *
 * @author abdulmalik
 */
public class SupplierAddItemResponse extends Response{
     private Item supplierItem;

    /**
     * @return the supplierItem
     */
    public Item getSupplierItem() {
        return supplierItem;
    }

    /**
     * @param supplierItem the supplierItem to set
     */
    public void setSupplierItem(Item supplierItem) {
        this.supplierItem = supplierItem;
    }

}

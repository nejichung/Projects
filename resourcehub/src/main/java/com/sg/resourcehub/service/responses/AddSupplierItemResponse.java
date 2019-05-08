/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Item;

/**
 *
 * @author Software Guld
 */
public class AddSupplierItemResponse extends Response {
    private Item addedSupItem;

    /**
     * @return the addedSupItem
     */
    public Item getAddedSupItem() {
        return addedSupItem;
    }

    /**
     * @param addedSupItem the addedSupItem to set
     */
    public void setAddedSupItem(Item addedSupItem) {
        this.addedSupItem = addedSupItem;
    }
}

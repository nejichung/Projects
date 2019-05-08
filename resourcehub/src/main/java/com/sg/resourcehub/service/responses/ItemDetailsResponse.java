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
public class ItemDetailsResponse extends Response {
    private Item singleItem;

    /**
     * @return the singleItem
     */
    public Item getSingleItem() {
        return singleItem;
    }

    /**
     * @param singleItem the singleItem to set
     */
    public void setSingleItem(Item singleItem) {
        this.singleItem = singleItem;
    }
}

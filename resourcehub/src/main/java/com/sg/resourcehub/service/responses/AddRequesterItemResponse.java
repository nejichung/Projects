/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Item;

/**
 *
 * @author Software Guld
 */
public class AddRequesterItemResponse extends Response{
    private Item addedRequesterItem;

    /**
     * @return the addedRequesterItem
     */
    public Item getAddedRequesterItem() {
        return addedRequesterItem;
    }

    /**
     * @param addedRequesterItem the addedRequesterItem to set
     */
    public void setAddedRequesterItem(Item addedRequesterItem) {
        this.addedRequesterItem = addedRequesterItem;
    }
}

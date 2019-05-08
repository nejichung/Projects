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
public class DeleteItemResponse extends Response{
    private Item deletedItem;

    /**
     * @return the deletedItem
     */
    public Item getDeletedItem() {
        return deletedItem;
    }

    /**
     * @param deletedItem the deletedItem to set
     */
    public void setDeletedItem(Item deletedItem) {
        this.deletedItem = deletedItem;
    }
}

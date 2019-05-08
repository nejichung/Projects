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
public class EditItemResponse extends Response{
    private Item editedItem;

    /**
     * @return the editedItem
     */
    public Item getEditedItem() {
        return editedItem;
    }

    /**
     * @param editedItem the editedItem to set
     */
    public void setEditedItem(Item editedItem) {
        this.editedItem = editedItem;
    }
}

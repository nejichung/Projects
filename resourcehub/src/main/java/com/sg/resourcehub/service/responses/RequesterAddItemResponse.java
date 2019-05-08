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
 * @author abdulmalik
 */
public class RequesterAddItemResponse extends Response{
   private Item requestItem;

    /**
     * @return the requestItem
     */
    public Item getRequestItem() {
        return requestItem;
    }

    /**
     * @param requestItem the requestItem to set
     */
    public void setRequestItem(Item requestItem) {
        this.requestItem = requestItem;
    }

}

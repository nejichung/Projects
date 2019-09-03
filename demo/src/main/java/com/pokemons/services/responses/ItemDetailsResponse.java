/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Item;

/**
 *
 * @author Jacob
 */
public class ItemDetailsResponse extends Response{
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.models;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author abdulmalik
 */
public class Requester {
    private Integer requesterId;
    
    @Size (max = 25, message = "Name must be less than 25 characters")
    @NotBlank (message = "Name must not be blank")
    private String requesterName;
    private List<Item> allItems;

    /**
     * @return the requesterId
     */
    public Integer getRequesterId() {
        return requesterId;
    }

    /**
     * @param requesterId the requesterId to set
     */
    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    /**
     * @return the requesterName
     */
    public String getRequesterName() {
        return requesterName;
    }

    /**
     * @param requesterName the requesterName to set
     */
    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    /**
     * @return the allItems
     */
    public List<Item> getAllItems() {
        return allItems;
    }

    /**
     * @param allItems the allItems to set
     */
    public void setAllItems(List<Item> allItems) {
        this.allItems = allItems;
    }
    
}

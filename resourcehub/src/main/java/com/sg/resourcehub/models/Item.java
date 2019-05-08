/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.models;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author abdulmalik
 */
public class Item {
    private Integer itemId;
    
    @Size (max = 25, message = "Name must be less than 25 characters")
    @NotBlank (message = "Name must not be blank")
    private String itemName;
    
    
    @NotNull (message = "Quantity must not be blank")
    private Integer Quantity;
    private List<Tag> allTags;
    private Integer requesterId;
    private Integer supplierId;
    private Supplier singleSup;
    private Requester singleReq;
    private String url;
    

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the Quantity
     */
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the allTags
     */
    public List<Tag> getAllTags() {
        return allTags;
    }

    /**
     * @param allTags the allTags to set
     */
    public void setAllTags(List<Tag> allTags) {
        this.allTags = allTags;
    }

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
     * @return the supplierId
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the singleSup
     */
    public Supplier getSingleSup() {
        return singleSup;
    }

    /**
     * @param singleSup the singleSup to set
     */
    public void setSingleSup(Supplier singleSup) {
        this.singleSup = singleSup;
    }

    /**
     * @return the singleReq
     */
    public Requester getSingleReq() {
        return singleReq;
    }

    /**
     * @param singleReq the singleReq to set
     */
    public void setSingleReq(Requester singleReq) {
        this.singleReq = singleReq;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}

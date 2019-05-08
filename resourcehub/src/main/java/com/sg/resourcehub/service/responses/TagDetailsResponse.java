/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Tag;

/**
 *
 * @author Software Guld
 */
public class TagDetailsResponse extends Response{
    private Tag singleTag;

    /**
     * @return the singleTag
     */
    public Tag getSingleTag() {
        return singleTag;
    }

    /**
     * @param singleTag the singleTag to set
     */
    public void setSingleTag(Tag singleTag) {
        this.singleTag = singleTag;
    }
}

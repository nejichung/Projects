/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.service.responses.Response;
import com.sg.resourcehub.models.Tag;
import java.util.List;

/**
 *
 * @author abdulmalik
 */
public class DisplayTagsResponse extends Response{
     private List<Tag> allTags;

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
}

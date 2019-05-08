/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.responses;

import com.sg.resourcehub.models.Requester;

/**
 *
 * @author Software Guld
 */
public class EditRequesterResponse extends Response {
    private Requester editedRequester;

    /**
     * @return the editedRequester
     */
    public Requester getEditedRequester() {
        return editedRequester;
    }

    /**
     * @param editedRequester the editedRequester to set
     */
    public void setEditedRequester(Requester editedRequester) {
        this.editedRequester = editedRequester;
    }
}

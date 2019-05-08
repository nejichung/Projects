/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.service;

import com.softwareguild.guessthenumber.dtos.Round;

/**
 *
 * @author Software Guld
 */
public class RoundResponse extends Response {
    private Round round;

    /**
     * @return the round
     */
    public Round getRound() {
        return round;
    }

    /**
     * @param round the round to set
     */
    public void setRound(Round round) {
        this.round = round;
    }
}

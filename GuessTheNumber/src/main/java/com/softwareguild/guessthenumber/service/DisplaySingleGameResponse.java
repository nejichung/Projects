/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.service;

import com.softwareguild.guessthenumber.dtos.Game;

/**
 *
 * @author Jacob
 */
public class DisplaySingleGameResponse extends Response {
    private Game singleGame;

    /**
     * @return the singleGame
     */
    public Game getSingleGame() {
        return singleGame;
    }

    /**
     * @param singleGame the singleGame to set
     */
    public void setSingleGame(Game singleGame) {
        this.singleGame = singleGame;
    }
}

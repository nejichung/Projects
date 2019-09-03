/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Move;

/**
 *
 * @author Jacob
 */
public class MoveDetailsResponse extends Response{
    private Move singleMove;

    /**
     * @return the singleMove
     */
    public Move getSingleMove() {
        return singleMove;
    }

    /**
     * @param singleMove the singleMove to set
     */
    public void setSingleMove(Move singleMove) {
        this.singleMove = singleMove;
    }
}

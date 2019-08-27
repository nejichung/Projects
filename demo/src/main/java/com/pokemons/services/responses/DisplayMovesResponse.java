/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Move;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class DisplayMovesResponse extends Response {
    private List<Move> allMoves;

    /**
     * @return the allMoves
     */
    public List<Move> getAllMoves() {
        return allMoves;
    }

    /**
     * @param allMoves the allMoves to set
     */
    public void setAllMoves(List<Move> allMoves) {
        this.allMoves = allMoves;
    }
}

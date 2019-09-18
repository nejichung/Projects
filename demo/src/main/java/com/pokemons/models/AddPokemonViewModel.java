/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.models;

import java.util.List;

/**
 *
 * @author Jacob
 */
public class AddPokemonViewModel {
    private List<Move> allMoves;
    private int[] selectedMovesIDs;
    private Pokemon poke;
    private Trainer singleTrainer;

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

    /**
     * @return the selectedMovesIDs
     */
    public int[] getSelectedMovesIDs() {
        return selectedMovesIDs;
    }

    /**
     * @param selectedMovesIDs the selectedMovesIDs to set
     */
    public void setSelectedMovesIDs(int[] selectedMovesIDs) {
        this.selectedMovesIDs = selectedMovesIDs;
    }

    /**
     * @return the poke
     */
    public Pokemon getPoke() {
        return poke;
    }

    /**
     * @param poke the poke to set
     */
    public void setPoke(Pokemon poke) {
        this.poke = poke;
    }

    /**
     * @return the singleTrainer
     */
    public Trainer getSingleTrainer() {
        return singleTrainer;
    }

    /**
     * @param singleTrainer the singleTrainer to set
     */
    public void setSingleTrainer(Trainer singleTrainer) {
        this.singleTrainer = singleTrainer;
    }
    
}

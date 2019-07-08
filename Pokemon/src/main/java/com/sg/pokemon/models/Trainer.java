/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokemon.models;

import java.util.List;

/**
 *
 * @author Jacob
 */
public class Trainer {
    private Integer trainerID;
    private String trainerName;
    private List<Pokemon> allPokemon;
    private List<Item> allItems;

    /**
     * @return the trainerID
     */
    public Integer getTrainerID() {
        return trainerID;
    }

    /**
     * @param trainerID the trainerID to set
     */
    public void setTrainerID(Integer trainerID) {
        this.trainerID = trainerID;
    }

    /**
     * @return the trainerName
     */
    public String getTrainerName() {
        return trainerName;
    }

    /**
     * @param trainerName the trainerName to set
     */
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    /**
     * @return the allPokemon
     */
    public List<Pokemon> getAllPokemon() {
        return allPokemon;
    }

    /**
     * @param allPokemon the allPokemon to set
     */
    public void setAllPokemon(List<Pokemon> allPokemon) {
        this.allPokemon = allPokemon;
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

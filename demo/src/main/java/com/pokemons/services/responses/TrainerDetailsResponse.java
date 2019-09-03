/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Trainer;

/**
 *
 * @author Jacob
 */
public class TrainerDetailsResponse extends Response {
    private Trainer singleTrainer;

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

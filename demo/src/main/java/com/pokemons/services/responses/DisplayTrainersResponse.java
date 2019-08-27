/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Trainer;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class DisplayTrainersResponse extends Response{
    private List<Trainer> allTrainers;

    /**
     * @return the allTrainers
     */
    public List<Trainer> getAllTrainers() {
        return allTrainers;
    }

    /**
     * @param allTrainers the allTrainers to set
     */
    public void setAllTrainers(List<Trainer> allTrainers) {
        this.allTrainers = allTrainers;
    }
}

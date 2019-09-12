/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

import com.pokemons.models.Trainer;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface TrainerDao {

    public List<Trainer> getAllTrainers() throws TrainerPersistenceException;

    public Trainer getTrainerByID(Integer id) throws TrainerPersistenceException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services;

import com.pokemons.daos.ItemDao;
import com.pokemons.daos.MoveDao;
import com.pokemons.daos.MovePersistenceException;
import com.pokemons.daos.PokePersistenceException;
import com.pokemons.daos.PokemonDao;
import com.pokemons.daos.TrainerDao;
import com.pokemons.daos.TrainerPersistenceException;
import com.pokemons.models.Move;
import com.pokemons.models.Pokemon;
import com.pokemons.models.Trainer;
import com.pokemons.services.responses.DisplayMovesResponse;
import com.pokemons.services.responses.DisplayPokemonResponse;
import com.pokemons.services.responses.DisplayTrainersResponse;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacob
 */
@Service
public class PokeService {
    
    @Autowired
    ItemDao itemDao;
    @Autowired
    MoveDao moveDao;
    @Autowired
    PokemonDao pokeDao;
    @Autowired
    TrainerDao trainerDao;

    public DisplayPokemonResponse getAllPokemon() {
       DisplayPokemonResponse response = new DisplayPokemonResponse();
       try{
       List<Pokemon> allPokemon = pokeDao.getAllPokemon();
       response.setAllPokemon(allPokemon);
       response.setSuccess(true);
    }catch(PokePersistenceException ex){
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
    }
       return response;
}

    public DisplayMovesResponse getAllMoves() {
        DisplayMovesResponse response = new DisplayMovesResponse();
        try{
            List<Move> allMoves = moveDao.getAllMoves();
            response.setAllMoves(allMoves);
            response.setSuccess(true);
        } catch(MovePersistenceException ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayTrainersResponse getAllTrainers() {
        DisplayTrainersResponse response = new DisplayTrainersResponse();
        try{
            List<Trainer> allTrainers = trainerDao.getAllTrainers();
            response.setAllTrainers(allTrainers);
            response.setSuccess(true);
        } catch(TrainerPersistenceException ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
    
}
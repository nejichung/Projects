/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services;

import com.pokemons.daos.ItemDao;
import com.pokemons.daos.ItemPersistenceException;
import com.pokemons.daos.MoveDao;
import com.pokemons.daos.MovePersistenceException;
import com.pokemons.daos.PokePersistenceException;
import com.pokemons.daos.PokemonDao;
import com.pokemons.daos.TrainerDao;
import com.pokemons.daos.TrainerPersistenceException;
import com.pokemons.models.Item;
import com.pokemons.models.Move;
import com.pokemons.models.Pokemon;
import com.pokemons.models.Trainer;
import com.pokemons.services.responses.DisplayItemsResponse;
import com.pokemons.services.responses.DisplayMovesResponse;
import com.pokemons.services.responses.DisplayPokemonResponse;
import com.pokemons.services.responses.DisplayTrainersResponse;
import com.pokemons.services.responses.MoveDetailsResponse;
import com.pokemons.services.responses.PokemonDetailsResponse;

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
        try {
            List<Pokemon> allPokemon = pokeDao.getAllPokemon();
            response.setAllPokemon(allPokemon);
            response.setSuccess(true);
        } catch (PokePersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayMovesResponse getAllMoves() {
        DisplayMovesResponse response = new DisplayMovesResponse();
        try {
            List<Move> allMoves = moveDao.getAllMoves();
            response.setAllMoves(allMoves);
            response.setSuccess(true);
        } catch (MovePersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayTrainersResponse getAllTrainers() {
        DisplayTrainersResponse response = new DisplayTrainersResponse();
        try {
            List<Trainer> allTrainers = trainerDao.getAllTrainers();
            response.setAllTrainers(allTrainers);
            response.setSuccess(true);
        } catch (TrainerPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DisplayItemsResponse getAllItems() {
        DisplayItemsResponse response = new DisplayItemsResponse();
        try {
            List<Item> allItems = itemDao.getAllItems();
            response.setAllItems(allItems);
            response.setSuccess(true);

        } catch (ItemPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public PokemonDetailsResponse getPokemonDetails(Integer id) {
        PokemonDetailsResponse response = new PokemonDetailsResponse();

        try {
            Pokemon singlePokemon = pokeDao.getPokemonByID(id);

            response.setSinglePokemon(singlePokemon);
            response.setSuccess(true);
        } catch (PokePersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public MoveDetailsResponse getMoveDetails(Integer id) {
        MoveDetailsResponse response = new MoveDetailsResponse();

        try {
            Move singleMove = moveDao.getMoveByID(id);

            response.setSingleMove(singleMove);
            response.setSuccess(true);
        } catch (MovePersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

}

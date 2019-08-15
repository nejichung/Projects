/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokemon.services;

import com.sg.pokemon.daos.ItemDBDao;
import com.sg.pokemon.daos.ItemDao;
import com.sg.pokemon.daos.MoveDBDao;
import com.sg.pokemon.daos.MoveDao;
import com.sg.pokemon.daos.PokePersistenceException;
import com.sg.pokemon.daos.PokemonDBDao;
import com.sg.pokemon.daos.PokemonDao;
import com.sg.pokemon.daos.TrainerDBDao;
import com.sg.pokemon.daos.TrainerDao;
import com.sg.pokemon.models.Pokemon;
import com.sg.pokemon.services.response.DisplayPokemonResponse;
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
}
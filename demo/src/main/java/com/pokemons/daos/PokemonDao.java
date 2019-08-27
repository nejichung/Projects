/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;


import com.pokemons.models.Pokemon;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface PokemonDao {

    public List<Pokemon> getAllPokemon() throws PokePersistenceException;
    
}

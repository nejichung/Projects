/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;


import com.pokemons.services.responses.Response;
import com.pokemons.models.Pokemon;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class DisplayPokemonResponse extends Response{
    private List<Pokemon> allPokemon;

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
    
    
}

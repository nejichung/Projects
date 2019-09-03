/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.services.responses;

import com.pokemons.models.Pokemon;

/**
 *
 * @author Jacob
 */
public class PokemonDetailsResponse extends Response {
    private Pokemon singlePokemon;

    /**
     * @return the singlePokemon
     */
    public Pokemon getSinglePokemon() {
        return singlePokemon;
    }

    /**
     * @param singlePokemon the singlePokemon to set
     */
    public void setSinglePokemon(Pokemon singlePokemon) {
        this.singlePokemon = singlePokemon;
    }
}

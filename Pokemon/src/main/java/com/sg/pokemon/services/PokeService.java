/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokemon.services;

import com.sg.pokemon.daos.ItemDBDao;
import com.sg.pokemon.daos.MoveDBDao;
import com.sg.pokemon.daos.PokemonDBDao;
import com.sg.pokemon.daos.TrainerDBDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacob
 */
@Service
public class PokeService {
    @Autowired
    ItemDBDao pokeDao;
    @Autowired
    MoveDBDao moveDao;
    @Autowired
    PokemonDBDao pokemonDao;
    @Autowired
    TrainerDBDao trainerDao;
}

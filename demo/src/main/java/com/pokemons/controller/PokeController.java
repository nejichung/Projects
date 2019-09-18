/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.controller;


import com.pokemons.models.AddPokemonViewModel;
import com.pokemons.models.Pokemon;
import com.pokemons.services.PokeService;
import com.pokemons.services.responses.DisplayPokemonResponse;
import com.pokemons.services.responses.PokemonDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class PokeController {
    
    @Autowired
    PokeService service;
    
    @GetMapping("/pokemon")
    public String ListPokemon(Model model){
        DisplayPokemonResponse response = service.getAllPokemon();
        model.addAttribute("allPokemon", response.getAllPokemon());
        
        return "pokemon";
    }
    
    @GetMapping("/pokemonDetails")
    public String pokemonDetails(Integer id, Model model){
        PokemonDetailsResponse response = service.getPokemonDetails(id);
        Pokemon singlePokemon = response.getSinglePokemon();
        model.addAttribute("singlePokemon", singlePokemon);
        
        return "pokemonDetails";
    }
    
    @GetMapping("addPokemon")
    public String addPokemon(){
        return "addPokemon";
    }
    @PostMapping("addPokemon")
    public String toAddPokemon(AddPokemonViewModel vm, Model model){
        return ListPokemon(model);
    }
}

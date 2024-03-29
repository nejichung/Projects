/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokemon.controller;

import com.sg.pokemon.services.PokeService;
import com.sg.pokemon.services.response.DisplayPokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

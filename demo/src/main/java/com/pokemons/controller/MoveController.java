/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.controller;

import com.pokemons.services.PokeService;
import com.pokemons.services.responses.DisplayMovesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class MoveController {
    
    @Autowired
    PokeService service;
    
    @GetMapping("/moves")
    public String ListMoves (Model model){
        DisplayMovesResponse response = service.getAllMoves();
        model.addAttribute("allMoves", response.getAllMoves());
        return "move";
}
}

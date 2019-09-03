/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.controller;

import com.pokemons.models.Item;
import com.pokemons.services.PokeService;
import com.pokemons.services.responses.DisplayItemsResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class ItemController {
    @Autowired
    PokeService service;
    
    @GetMapping("/items")
    public String viewItems(Model model){
        DisplayItemsResponse response = service.getAllItems();
        model.addAttribute("allItems", response.getAllItems());
        return "item";
    }
    
}

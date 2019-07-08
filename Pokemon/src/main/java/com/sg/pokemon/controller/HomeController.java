/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class HomeController {
    
    @GetMapping("")
    public String homePage (Model model){
        return "Home";
    }
    @GetMapping("/items")
    public String items (Model model){
        return "items";
    }
    @GetMapping("/pokemon")
    public String pokemon (Model model){
        return "pokemon";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.controller;

import com.pokemons.models.Trainer;
import com.pokemons.services.PokeService;
import com.pokemons.services.responses.DisplayTrainersResponse;
import com.pokemons.services.responses.TrainerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class TrainerController {
    
    @Autowired
    PokeService service;
    
    @GetMapping("/trainers")
    public String ListTrainers(Model model){
        DisplayTrainersResponse response = service.getAllTrainers();
        model.addAttribute("allTrainers", response.getAllTrainers());
        
        return "trainer";
    }
    
    @GetMapping("/trainerDetails")
    public String TrainerDetails(Integer id, Model model){
        TrainerDetailsResponse response = service.getTrainerDetails(id);
        Trainer singleTrainer = response.getSingleTrainer();
        model.addAttribute("singleTrainer", singleTrainer);
        
        return "trainerDetails";
        
    }
}

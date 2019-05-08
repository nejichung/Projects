/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku;

import com.sg.deku.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Jacob
 */
@Controller
public class HomeController {
    
    @Autowired
    HeroService service;
    
   @GetMapping("/")
    public String MainMenu(Model model) {
        return "home";
    }
    
    
}

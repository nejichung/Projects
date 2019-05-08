/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.DisplayItemsResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author abdulmalik
 */
@Controller
public class HomeController {
    @Autowired
    ResourceService service;
    
     @GetMapping("/")
public String homePage(Model model){
    DisplayItemsResponse response = service.getAllItems();
        List<Item> listOfItems = response.getAllItems();
        model.addAttribute("allItems", listOfItems);
    return "Items";
}

     @GetMapping ("/aboutUs")
    public String displayAboutUS(Model model){
        return "AboutUs";
    }

        @GetMapping("/contactUs")
    public String displayContactUs(Model model){
        return "ContactUs";
    }
}

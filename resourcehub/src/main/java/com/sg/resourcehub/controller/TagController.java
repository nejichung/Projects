/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Tag;
import com.sg.resourcehub.service.responses.DisplayTagsResponse;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.TagDetailsResponse;
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
public class TagController {
     @Autowired
    ResourceService service;
     
        @GetMapping("/tags") // not necessary to list all tags
    public String displaytags(Model model) {
        
       DisplayTagsResponse response  = service.getAllTags();
       List<Tag> listOfTags = response.getAllTags();
       model.addAttribute("allTags", listOfTags);
        return "Tags";
    }
    @GetMapping("tagDetails")
    public String tagDetails(Integer id, Model model){
        TagDetailsResponse response = service.getTagDetails(id);
        Tag tag = response.getSingleTag();
        model.addAttribute("tag", tag);
        return "TagDetails";
    }
}

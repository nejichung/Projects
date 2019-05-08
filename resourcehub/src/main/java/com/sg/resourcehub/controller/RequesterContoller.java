/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.models.Requester;
import com.sg.resourcehub.service.responses.DisplayAllRequestersResponse;
import com.sg.resourcehub.service.responses.RequesterDetailsResponse;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.AddRequesterItemResponse;
import com.sg.resourcehub.service.responses.AddRequesterResponse;
import com.sg.resourcehub.service.responses.DeleteRequesterResponse;
import com.sg.resourcehub.service.responses.EditRequesterResponse;
import com.sg.resourcehub.service.responses.GetRequesterByIDResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author abdulmalik
 */
@Controller
public class RequesterContoller {
     @Autowired
    ResourceService service;
     
       @GetMapping("/requesters")
    public String displayRequesters(Model model) {
        
       DisplayAllRequestersResponse response  = service.getAllRequesters();
       List<Requester> listOfRequests = response.getAllRequesters();
       model.addAttribute("allRequesters", listOfRequests);
        return "Requesters";
    }
    @GetMapping("requesterDetails")
    public String requesterDetails(Integer id, Model model) {
        RequesterDetailsResponse response = service.getRequesterDetails(id);
        Requester requester = response.getSingleRequester();
        model.addAttribute("req", requester);
        return "RequesterDetails";
    }
    @GetMapping("/addRequester")
    public String displayAddRequester(Model model){
        return "AddRequester";
    }
    @PostMapping("/addRequester")
    public String addRequester(@ModelAttribute Requester toAdd, Model model){
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Requester>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors", validationFailures);
        
        if(validationFailures.isEmpty()){
            AddRequesterResponse response = service.addRequester(toAdd);
        } else {
            model.addAttribute("requesterName", toAdd.getRequesterName());
        }
        return displayRequesters(model);
    }
    @PostMapping("/addRequesterItem")
    public String addPicture(HttpServletRequest request,
            Model model,
            @RequestParam("itemName") String itemName,
            @RequestParam("quantity") Integer qty,
            @RequestParam("requesterId")Integer requesterId,
            @RequestParam("picture") MultipartFile pictureFile
    ) {
        if (!pictureFile.isEmpty()) {
            try {
                Path dirPath = Paths.get(request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/"), "image");
                File dir = new File(dirPath.toUri());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                Path filePath = Paths.get(request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/"), "image", pictureFile.getOriginalFilename() );
                
                //String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(filePath.toFile());
                Item toAdd = new Item();
                toAdd.setItemName(itemName);
                toAdd.setQuantity(qty);
                toAdd.setRequesterId(requesterId);
                          
                String imgUrl = "/image/"+pictureFile.getOriginalFilename();
                toAdd.setUrl(imgUrl);
                
                AddRequesterItemResponse response = service.addRequesterItem(toAdd);
                
                return "redirect:/items";
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed: "
                        + e.getMessage());
                return "supplierItem";
            }
        } else {
            model.addAttribute("errorMsg",
                    "Please specify a non-empty file.");
            return "supplierItem";
        }

    }
     @GetMapping("/deleteRequester")
    public String deleteRequester(Model model, Integer id){
        DeleteRequesterResponse response = service.deleteRequester(id);
        return "redirect:/requesters";
    }
    @GetMapping("/editRequester")
    public String editRequester(Model model, Integer id){
       GetRequesterByIDResponse response = service.getRequesterByID(id);
       Requester editRequester = response.getSingleRequester();
       model.addAttribute("editRequester", editRequester);
       return "EditRequester";
    }
    @PostMapping("/editRequester")
    public String executeEditRequester(Model model, Requester editRequester){
        EditRequesterResponse editResponse= service.editRequester(editRequester);
        model.addAttribute("editedRequester", editResponse.getEditedRequester());
        return "redirect:/requesters";
    }
}

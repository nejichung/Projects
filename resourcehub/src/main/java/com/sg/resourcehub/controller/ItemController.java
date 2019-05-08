/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.models.Requester;
import com.sg.resourcehub.models.Supplier;
import com.sg.resourcehub.service.responses.DisplayItemsResponse;
import com.sg.resourcehub.service.responses.ItemDetailsResponse;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.DeleteItemResponse;
import com.sg.resourcehub.service.responses.DisplayAllRequestersResponse;
import com.sg.resourcehub.service.responses.DisplayAllSuppliersResponse;
import com.sg.resourcehub.service.responses.EditItemResponse;
import com.sg.resourcehub.service.responses.GetItemByIDResponse;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author abdulmalik
 */
@Controller
public class ItemController {

    @Autowired
    ResourceService service;

    @GetMapping("/items")
    public String displayItems(Model model) {

        DisplayItemsResponse response = service.getAllItems();
        List<Item> listOfItems = response.getAllItems();
        model.addAttribute("allItems", listOfItems);
        return "Items";
    }

//    @GetMapping("/supplierItem")
//    public String supplyItem(Model model) {
//        SupplierAddItemResponse response = service.getSupplierItem(itemName, 0);
//        Item addItem = response.getSupplierItem();
//        model.addAttribute("suppliedItem", addItem);
//
//        return "Items";
//    }
//
//    @GetMapping("/requesterItem")
//    public String requestItem(Model model) {
//      RequesterAddItemResponse response = service.getRequestItem(itemName, 0);
//        return "Items";
//    }

    @GetMapping("itemDetails")
    public String itemDetails(Integer id, Model model) {
        ItemDetailsResponse response = service.getItemDetails(id);
        Item item = response.getSingleItem();
        model.addAttribute("item", item);
        return "ItemDetails";
    }
     @GetMapping ("/supplierItem")
    public String displaySupplierItem(Model model){
        DisplayAllSuppliersResponse response = service.getAllSuppliers();
        List<Supplier> listOfSuppliers = response.getAllSuppliers();
        model.addAttribute("allSuppliers", listOfSuppliers);
        return "supplierItem";
    }
//    @PostMapping("/addSupplierItem")
//    public String addSupplier(@ModelAttribute Item toAdd, Model model){
//        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<Item>> validationFailures = validate.validate(toAdd);
//        model.addAttribute("errors", validationFailures);
//        
//        if(validationFailures.isEmpty()){
//            AddSupplierItemResponse response = service.addSupplierItem(toAdd);
//        } else {
//            model.addAttribute("itemName", toAdd.getItemName());
//            model.addAttribute("quantity", toAdd.getQuantity());
//        }
//        return displayItems(model);
//    }
        @GetMapping("/requesterItem")
    public String displayRequesterItem(Model model){
        DisplayAllRequestersResponse response  = service.getAllRequesters();
       List<Requester> listOfRequests = response.getAllRequesters();
       model.addAttribute("allRequesters", listOfRequests);
        return "requesterItem";
    }
    
    @GetMapping("/deleteItem")
    public String deleteItem(Model model, Integer id){
        DeleteItemResponse response = service.deleteItem(id);
        return "redirect:/items";
    }
    @GetMapping("editItem")
    public String editItem(Integer id, Model model){
       DisplayAllSuppliersResponse response = service.getAllSuppliers();
        List<Supplier> listOfSuppliers = response.getAllSuppliers();
        model.addAttribute("allSuppliers", listOfSuppliers);
        
        DisplayAllRequestersResponse response2  = service.getAllRequesters();
       List<Requester> listOfRequests = response2.getAllRequesters();
       model.addAttribute("allRequesters", listOfRequests);
        
        GetItemByIDResponse response3 = service.getItemByID(id);
        Item editItem = response3.getSingleItem();
        model.addAttribute("editItem", editItem);
        
        return "EditItem";
    }
    
    @PostMapping("editItem")
    public String executeEditItem(HttpServletRequest request, Model model, Item editItem, @RequestParam("picture") MultipartFile pictureFile){
        if (editItem.getUrl() == null && pictureFile == null){
            GetItemByIDResponse response = service.getItemByID(editItem.getItemId());
            editItem.setUrl(response.getSingleItem().getUrl());
        }
        else if (editItem.getUrl() == null){
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
                String imgUrl = "/image/"+pictureFile.getOriginalFilename();
                editItem.setUrl(imgUrl);
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
        EditItemResponse editResponse = service.editItem(editItem);
        model.addAttribute("editedItem", editResponse.getEditedItem()); // might not be necessary
        //basically check to see if the url is null and if it is null then we want to u
        // try to update that url to whatever was choses/ the original image from the original item from service.
        // will need to do the same thing when adding the item with image
       return "redirect:/items";
    

}
}

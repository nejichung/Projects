/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller;

import com.sg.resourcehub.models.Item;
import com.sg.resourcehub.models.Supplier;
import com.sg.resourcehub.service.responses.DisplayAllSuppliersResponse;
import com.sg.resourcehub.service.ResourceService;
import com.sg.resourcehub.service.responses.AddSupplierItemResponse;
import com.sg.resourcehub.service.responses.AddSupplierResponse;
import com.sg.resourcehub.service.responses.DeleteSupplierResponse;
import com.sg.resourcehub.service.responses.EditSupplierResponse;
import com.sg.resourcehub.service.responses.GetSupplierByIDResponse;
import com.sg.resourcehub.service.responses.SupplierDetailsResponse;
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
public class SupplierController {

    @Autowired
    ResourceService service;

    @GetMapping("/suppliers")
    public String displaySuppliers(Model model) {

        DisplayAllSuppliersResponse response = service.getAllSuppliers();
        List<Supplier> listOfSuppliers = response.getAllSuppliers();
        model.addAttribute("allSuppliers", listOfSuppliers);
        return "Suppliers";
    }

    @PostMapping("/addSupplierItem")
    public String addPicture(HttpServletRequest request,
            Model model,
            @RequestParam("itemName") String itemName,
            @RequestParam("quantity") Integer qty,
            @RequestParam("supplierId")Integer supplierId,
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
                toAdd.setSupplierId(supplierId);
                          
                String imgUrl = "/image/"+pictureFile.getOriginalFilename();
                toAdd.setUrl(imgUrl);
                
                AddSupplierItemResponse response = service.addSupplierItem(toAdd);
                
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

    @GetMapping("supplierDetails")
    public String supplierDetails(Integer id, Model model) {
        SupplierDetailsResponse response = service.getSupplierDetails(id);
        Supplier supplier = response.getSingleSupplier();
        model.addAttribute("sup", supplier);
        return "SupplierDetails";
    }

    @GetMapping("/addSupplier")
    public String displayAddSupplier(Model model) {
        return "AddSupplier";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@ModelAttribute Supplier toAdd, Model model) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Supplier>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors", validationFailures);

        if (validationFailures.isEmpty()) {
            AddSupplierResponse response = service.addSupplier(toAdd);
        } else {
            model.addAttribute("supplierName", toAdd.getSupplierName());
            model.addAttribute("supplierAddress", toAdd.getSupplierAddress());
        }
        return displaySuppliers(model);
    }
    
     @GetMapping("/deleteSupplier")
    public String deleteSupplier(Model model, Integer id){
        DeleteSupplierResponse response = service.deleteSupplier(id);
        return "redirect:/suppliers";
    }
    @GetMapping("/editSupplier")
    public String editSupplier(Model model, Integer id){
       GetSupplierByIDResponse response = service.getSupplierByID(id);
       Supplier editSupplier = response.getSingleSupplier();
       model.addAttribute("editSupplier", editSupplier);
       return "EditSupplier";
    }
    @PostMapping("/editSupplier")
    public String executeEditSupplier(Model model, Supplier editSupplier){
        EditSupplierResponse editResponse= service.editSupplier(editSupplier);
        model.addAttribute("editedSupplier", editResponse.getEditedSupplier());
        return "redirect:/suppliers";
    }
}

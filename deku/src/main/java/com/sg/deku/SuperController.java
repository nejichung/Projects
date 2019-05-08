/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku;

import com.sg.deku.models.AddSuperViewModel;
import com.sg.deku.models.EditSuperViewModel;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import com.sg.deku.services.responses.AddSuperResponse;
import com.sg.deku.services.responses.DisplaySupersResponse;
import com.sg.deku.services.HeroService;
import com.sg.deku.services.responses.DeleteSuperResponse;
import com.sg.deku.services.responses.DisplayOrganizationsResponse;
import com.sg.deku.services.responses.DisplaySightingsResponse;
import com.sg.deku.services.responses.EditSuperResponse;
import com.sg.deku.services.responses.GetOrganizationByIDResponse;
import com.sg.deku.services.responses.GetSightingByIDResponse;
import com.sg.deku.services.responses.GetSuperByIDResponse;
import com.sg.deku.services.responses.SuperDetailsResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class SuperController {
    
    @Autowired
    HeroService service;
    
    
    @GetMapping("/supers")
    public String ListSupers(Model model) {
        DisplaySupersResponse response = service.getAllSupers();
        model.addAttribute("allSupers", response.getAllSupers());
        DisplaySightingsResponse response2 = service.getAllSightings();
        model.addAttribute("allSightings", response2.getAllSightings());
        DisplayOrganizationsResponse response3 = service.getAllOrganizations();
        model.addAttribute("allOrganizations", response3.getAllOrganizations());
        return "supers";
    }
    @GetMapping("superDetails")
    public String superDetails(Integer id, Model model){
        SuperDetailsResponse response = service.getSuperDetails(id);
        Super singleSuper = response.getSingleSuper();
        model.addAttribute("singleSuper", singleSuper);
        return "superDetails";
    }
    
    @PostMapping("/addSuper") // may need to work on
    public String addSuper(AddSuperViewModel vm, Model model){
        
        List<Organization> selectedOrgs = new ArrayList<Organization>();
        int[] selectedIds = vm.getSelectedOrganizationIDs();
        if(selectedIds != null){
        for(int i = 0; i < selectedIds.length; i++){
            int orgId = selectedIds[i];
            
            GetOrganizationByIDResponse response = service.getOrganizationByID(orgId);
            selectedOrgs.add(response.getSingleOrganization());
        }
        }
        List<Sighting> selectedSightings = new ArrayList<Sighting>();
        int[] selectedSightingIds = vm.getSelectedSightingIDs();
        if(selectedSightingIds != null){
        for(int i = 0; i < selectedSightingIds.length; i++){
            int sightingId = selectedSightingIds[i];
            
            GetSightingByIDResponse response = service.getSightingByID(sightingId);
            selectedSightings.add(response.getSingleSighting());
        }
        }
        vm.getSup().setAllSightings(selectedSightings);
        vm.getSup().setAllOrganizations(selectedOrgs);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Super>> validationFailures = validate.validate(vm.getSup());
        model.addAttribute("errors",validationFailures);
        
        if( validationFailures.isEmpty()){
            AddSuperResponse response = service.addSuper(vm.getSup());
        } else{
            model.addAttribute("superName", vm.getSup().getSuperName());
            model.addAttribute("description", vm.getSup().getDescription());
            model.addAttribute("quirk", vm.getSup().getQuirk());
        }
       return ListSupers(model); 
    }
    @GetMapping("deleteSuper")
    public String deleteSuper(Integer id){
        DeleteSuperResponse response = service.deleteSuper(id);
        return "redirect:/supers";
    }
    
    @GetMapping("editSuper")
    public String editSuper(Integer id, Model model){
        EditSuperViewModel vm = new EditSuperViewModel();
        GetSuperByIDResponse response = service.getSuperByID(id);
        DisplaySightingsResponse sightingResponse = service.getAllSightings();
        DisplayOrganizationsResponse orgResponse = service.getAllOrganizations();
        
        vm.setSup(response.getSingleSuper());
        vm.setAllOrganizations(orgResponse.getAllOrganizations());
        vm.setAllSightings(sightingResponse.getAllSightings());
        model.addAttribute("vm", vm);
        
        return "editSuper";
    }
    
    @PostMapping("editSuper")
    public String executeEditSuper(EditSuperViewModel vm, Model model){ 
        List<Organization> selectedOrganizations = new ArrayList<Organization>();
        
        int[] selectedIds = vm.getSelectedOrganizationIDs();
        if(selectedIds != null){
        for(int i = 0; i < selectedIds.length; i++){
            int orgId = selectedIds[i];
            
            GetOrganizationByIDResponse response = service.getOrganizationByID(orgId);
            selectedOrganizations.add(response.getSingleOrganization());
        }
        }
        
       List<Sighting> selectedSightings = new ArrayList<Sighting>();
       
        int[] selectedSightingIds = vm.getSelectedSightingIDs();
        if(selectedSightingIds !=null){
        for(int i = 0; i < selectedSightingIds.length; i++){
            int sightingId = selectedSightingIds[i];
            
            GetSightingByIDResponse response = service.getSightingByID(sightingId);
            selectedSightings.add(response.getSingleSighting());
        }
        }
        vm.getSup().setAllSightings(selectedSightings);
        vm.getSup().setAllOrganizations(selectedOrganizations);
        EditSuperResponse editResponse = service.editSuper(vm.getSup());
        model.addAttribute("editedSuper", editResponse.getEditedSuper());
        
        return "redirect:/supers";
                }
    
}

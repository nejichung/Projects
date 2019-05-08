/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku;

import com.sg.deku.models.AddOrgViewModel;
import com.sg.deku.models.EditOrgViewModel;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Super;
import com.sg.deku.services.responses.DisplayOrganizationsResponse;
import com.sg.deku.services.HeroService;
import com.sg.deku.services.responses.AddOrganizationResponse;
import com.sg.deku.services.responses.DeleteOrganizationResponse;
import com.sg.deku.services.responses.DisplaySupersResponse;
import com.sg.deku.services.responses.EditOrganizationResponse;
import com.sg.deku.services.responses.GetOrganizationByIDResponse;
import com.sg.deku.services.responses.GetSuperByIDResponse;
import com.sg.deku.services.responses.OrganizationDetailsResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class OrganizationController {
    
    @Autowired
    HeroService service;
    
    @GetMapping("/organizations")
    public String ListOrganizations(Model model) {
        DisplayOrganizationsResponse response = service.getAllOrganizations();
        model.addAttribute("allOrganizations", response.getAllOrganizations());
        DisplaySupersResponse response2 = service.getAllSupers();
        model.addAttribute("allSupers", response2.getAllSupers());
        return "organizations";
    }
    
    @GetMapping("organizationDetails")
    public String organizationDetails(Integer id, Model model){
        OrganizationDetailsResponse response = service.getOrganizationsDetails(id);
        Organization organization = response.getOrganization();
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }
    
    @PostMapping("addOrganization")
    public String addOrganization(AddOrgViewModel vm, Model model){
         List<Super> selectedSupers = new ArrayList<Super>();
        int[] selectedIds = vm.getSelectedSuperIDs();
        if(selectedIds != null){
        for(int i = 0; i < selectedIds.length; i++){
            int supId = selectedIds[i];
            
            GetSuperByIDResponse response = service.getSuperByID(supId);
            selectedSupers.add(response.getSingleSuper());
        }
        }
        vm.getOrg().setAllSupers(selectedSupers);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Organization>> validationFailures = validate.validate(vm.getOrg());
        model.addAttribute("errors",validationFailures);
        
        if( validationFailures.isEmpty()){
            AddOrganizationResponse response = service.addOrganization(vm.getOrg());
        } else{
            model.addAttribute("organizationName", vm.getOrg().getOrganizationName());
            model.addAttribute("description", vm.getOrg().getDescription());
            model.addAttribute("address", vm.getOrg().getAddress());
            model.addAttribute("phoneNumber", vm.getOrg().getPhoneNumber());
            model.addAttribute("emailAddress", vm.getOrg().getEmailAddress());
        }
       return ListOrganizations(model); 
    }
    
     @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id){
        DeleteOrganizationResponse response = service.deleteOrganization(id);
        return "redirect:/organizations";
    }


    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model){
       EditOrgViewModel vm = new EditOrgViewModel();
        GetOrganizationByIDResponse response = service.getOrganizationByID(id);
        DisplaySupersResponse superResponse = service.getAllSupers();
        
        vm.setOrg(response.getSingleOrganization());
        vm.setAllSupers(superResponse.getAllSupers());
        model.addAttribute("vm", vm);
        
        return "editOrganization";
    }
    
    @PostMapping("editOrganization") // david's stuff
    public String performEditOrganization(EditOrgViewModel vm, Model model){
        
            List<Super> selectedSupers = new ArrayList<Super>();
            int[] selectedIds = vm.getSelectedSuperIDs();
            if( selectedIds != null){
            for( int i = 0; i < selectedIds.length; i++){
                int superId = selectedIds[i];
                
                GetSuperByIDResponse response = service.getSuperByID(superId);
                selectedSupers.add(response.getSingleSuper());
            }
            }
            vm.getOrg().setAllSupers(selectedSupers);
            EditOrganizationResponse editResponse = service.editOrg(vm.getOrg());
            model.addAttribute("editedOrg", editResponse.getEditedOrg());
//        EditOrgViewModel vm = new EditOrgViewModel();
//        GetOrganizationByIDResponse response = service.getOrganizationByID(id);
//        DisplaySupersResponse superResponse = service.getAllSupers();
//        
//        vm.setOrg(response.getSingleOrganization());
//        vm.setAllSupers(superResponse.getAllSupers());
        return "redirect:/organizations";
    }
    
}

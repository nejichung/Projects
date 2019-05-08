/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku;

import com.sg.deku.models.AddSightingViewModel;
import com.sg.deku.models.EditSightingViewModel;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import com.sg.deku.services.responses.DisplaySightingsResponse;
import com.sg.deku.services.HeroService;
import com.sg.deku.services.responses.AddSightingResponse;
import com.sg.deku.services.responses.DeleteSightingResponse;
import com.sg.deku.services.responses.DisplayLocationsResponse;
import com.sg.deku.services.responses.DisplaySupersResponse;
import com.sg.deku.services.responses.EditSightingResponse;
import com.sg.deku.services.responses.GetLocationByIDResponse;
import com.sg.deku.services.responses.GetSightingByIDResponse;
import com.sg.deku.services.responses.GetSuperByIDResponse;
import com.sg.deku.services.responses.SightingDetailsResponse;
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
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jacob
 */
@Controller
public class SightingController {

    @Autowired
    HeroService service;

    @GetMapping("/sightings")
    public String ListSightings(Model model) {
        DisplaySightingsResponse response = service.getAllSightings();
        model.addAttribute("allSightings", response.getAllSightings());
        DisplaySupersResponse response2 = service.getAllSupers();
        model.addAttribute("allSupers", response2.getAllSupers());
        DisplayLocationsResponse response3 = service.getAllLocations();
        model.addAttribute("allLocations", response3.getAllLocations());
        return "sightings";
    }

    @GetMapping("sightingDetails")
    public String sightingDetails(Integer id, Model model) {
        SightingDetailsResponse response = service.getSightingDetails(id);
        Sighting sighting = response.getSighting();
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }

    @PostMapping("/addSighting") // don't know if i can even set a superID in my dto since it's many-many
    public String addSighting(AddSightingViewModel vm, Model model
            
//            @RequestParam("superID") Integer superID,
//            @RequestParam("locationID") Integer locationID,
//            @RequestParam("date") LocalDate 
                    ) {
        
        
        List<Super> selectedSupers = new ArrayList<Super>();
        int[] selectedIds = vm.getSelectedSuperIDs();
        if(selectedIds != null){
        for(int i = 0; i < selectedIds.length; i++){
            int supId = selectedIds[i];
            
            GetSuperByIDResponse response = service.getSuperByID(supId);
            selectedSupers.add(response.getSingleSuper());
        }
        }
        vm.getSighting().setAllSupers(selectedSupers);
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Sighting>> validationFailures = validate.validate(vm.getSighting());
        model.addAttribute("errors", validationFailures);
        if (validationFailures.isEmpty()) {
            AddSightingResponse response = service.addSighting(vm.getSighting());
        } else {
            model.addAttribute("locationID", vm.getSighting().getLocationID());
            model.addAttribute("date", vm.getSighting().getDate());
//            model.addAttribute("superID", vm.getSighting().getSuperID());
        }
        return ListSightings(model);
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        DeleteSightingResponse response = service.deleteSighting(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        EditSightingViewModel vm = new EditSightingViewModel();
        GetSightingByIDResponse response = service.getSightingByID(id);
        DisplaySupersResponse superResponse = service.getAllSupers();
        DisplayLocationsResponse locResponse = service.getAllLocations();
        vm.setAllLocations(locResponse.getAllLocations());
        vm.setSighting(response.getSingleSighting());
        vm.setAllSupers(superResponse.getAllSupers());
        model.addAttribute("vm", vm);

        return "editSighting";
    }
    
    @PostMapping("editSighting")
    public String executeEditSighting(EditSightingViewModel vm, Model model){
        
        List<Super> selectedSupers = new ArrayList<Super>();
            int[] selectedIds = vm.getSelectedIDs();
            if (selectedIds != null){
            for( int i = 0; i < selectedIds.length; i++){
                int superId = selectedIds[i];
                
                GetSuperByIDResponse response = service.getSuperByID(superId);
                selectedSupers.add(response.getSingleSuper());
            }
            }
            vm.getSighting().setAllSupers(selectedSupers);
            GetLocationByIDResponse response2 = service.getLocationByID(vm.getSighting().getLocationID());
            vm.getSighting().setSingleLocation(response2.getSingleLocation());
            EditSightingResponse editResponse = service.editSighting(vm.getSighting());
            model.addAttribute("editedSighting", editResponse.getEditedSighting());
            
            return "redirect:/sightings";
    }
}

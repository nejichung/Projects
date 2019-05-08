/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku;

import com.sg.deku.models.EditLocationViewModel;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import com.sg.deku.services.responses.AddLocationResponse;
import com.sg.deku.services.responses.DisplayLocationsResponse;
import com.sg.deku.services.HeroService;
import com.sg.deku.services.responses.DeleteLocationResponse;
import com.sg.deku.services.responses.DisplaySightingsResponse;
import com.sg.deku.services.responses.EditLocationResponse;
import com.sg.deku.services.responses.GetLocationByIDResponse;
import com.sg.deku.services.responses.GetSightingByIDResponse;
import com.sg.deku.services.responses.LocationDetailsResponse;
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
public class LocationController {

    @Autowired
    HeroService service;
    
    @GetMapping("/locations")
    public String ListLocations(Model model) {
        DisplayLocationsResponse response = service.getAllLocations();
        model.addAttribute("allLocations", response.getAllLocations());
        return "locations";
    }
    
    @GetMapping("locationDetails")
    public String locationDetails(Integer id, Model model){
            LocationDetailsResponse response = service.getLocationDetails(id); // null
            Location location = response.getLocation();
//        Location location = 
        model.addAttribute("location", location);
        return "locationDetails";
    }
    
    @PostMapping("/addLocation") //WORKS
    public String addLocation(@ModelAttribute Location toAdd, Model model ){ // <-- Ideal way of pulling in objects ,this toAdd will come in populated
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator(); // WTF IS THIS
        Set<ConstraintViolation<Location>> validationFailures = validate.validate(toAdd);
        model.addAttribute("errors",validationFailures);
        
        if( validationFailures.isEmpty()){ // service should have the same validations as these
            AddLocationResponse response = service.addLocation(toAdd);
            
        } else{ // so if the page is refreshed. the correct fields are not lost
            model.addAttribute("locationName", toAdd.getLocationName());
            model.addAttribute("description", toAdd.getDescription());
            model.addAttribute("address", toAdd.getAddress());
            model.addAttribute("latitude", toAdd.getLatitude());
            model.addAttribute("longitude", toAdd.getLongitude());
            
            
        }
        return ListLocations(model); // calling to the method above it deos both model changes and errors. rather than redirecting and losing the errors
    }
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        DeleteLocationResponse response = service.deleteLocation(id);
        return "redirect:/locations";
    }
    
    @GetMapping("/editLocation") //@ModelAttribute Location toEdit
    public String editLocation( Integer id, Model model){  // FAIL
//        int id = Integer.parseInt(request.getParameter("locationID"));
        EditLocationViewModel vm = new EditLocationViewModel();
        
        GetLocationByIDResponse response = service.getLocationByID(id);
        DisplaySightingsResponse sightingResponse = service.getAllSightings();
        vm.setLocation(response.getSingleLocation());
        vm.setAllSightings(sightingResponse.getAllSightings());
        model.addAttribute("vm", vm);
        return "editLocation";
    }
    
    @PostMapping("/editLocation")
    public String executeEditLocation(EditLocationViewModel vm, Model model){
//        List<Sighting> selectedSightings = new ArrayList<Sighting>();
//        int[] selectedIds = vm.getSelectedSightingIDs();
//        for(int i = 0; i < selectedIds.length; i++){
//            int sightingId = selectedIds[i];
//            
//            GetSightingByIDResponse response = service.getSightingByID(sightingId);
//            selectedSightings.add(response.getSingleSighting());
//        }
//        vm.getLocation().setAllSightings(selectedSightings);
        EditLocationResponse editResponse = service.editLocation(vm.getLocation());
        model.addAttribute("editedLocation", editResponse.getEditedLocation());
        
        return "redirect:/locations";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services;

import com.sg.deku.services.responses.SuperDetailsResponse;
import com.sg.deku.services.responses.SightingDetailsResponse;
import com.sg.deku.services.responses.OrganizationDetailsResponse;
import com.sg.deku.services.responses.LocationDetailsResponse;
import com.sg.deku.services.responses.DisplaySupersResponse;
import com.sg.deku.services.responses.DisplaySightingsResponse;
import com.sg.deku.services.responses.DisplayOrganizationsResponse;
import com.sg.deku.services.responses.DisplayLocationsResponse;
import com.sg.deku.services.responses.AddSuperResponse;
import com.sg.deku.services.responses.AddLocationResponse;
import com.sg.deku.daos.LocationDao;
import com.sg.deku.daos.LocationPersistenceException;
import com.sg.deku.daos.OrganizationDao;
import com.sg.deku.daos.OrganizationPersistenceException;
import com.sg.deku.daos.SightingDao;
import com.sg.deku.daos.SightingPersistenceException;
import com.sg.deku.daos.SuperDao;
import com.sg.deku.daos.SuperPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import com.sg.deku.services.exceptions.InvalidAddressException;
import com.sg.deku.services.exceptions.InvalidDateException;
import com.sg.deku.services.exceptions.InvalidDescriptionException;
import com.sg.deku.services.exceptions.InvalidEmailAddressException;
import com.sg.deku.services.exceptions.InvalidLatitudeException;
import com.sg.deku.services.exceptions.InvalidLongitudeException;
import com.sg.deku.services.exceptions.InvalidNameException;
import com.sg.deku.services.exceptions.InvalidPhoneNumberException;
import com.sg.deku.services.exceptions.InvalidQuirkException;
import com.sg.deku.services.responses.AddOrganizationResponse;
import com.sg.deku.services.responses.AddSightingResponse;
import com.sg.deku.services.responses.DeleteLocationResponse;
import com.sg.deku.services.responses.DeleteOrganizationResponse;
import com.sg.deku.services.responses.DeleteSightingResponse;
import com.sg.deku.services.responses.DeleteSuperResponse;
import com.sg.deku.services.responses.EditLocationResponse;
import com.sg.deku.services.responses.EditOrganizationResponse;
import com.sg.deku.services.responses.EditSightingResponse;
import com.sg.deku.services.responses.EditSuperResponse;
import com.sg.deku.services.responses.GetLocationByIDResponse;
import com.sg.deku.services.responses.GetOrganizationByIDResponse;
import com.sg.deku.services.responses.GetSightingByIDResponse;
import com.sg.deku.services.responses.GetSuperByIDResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jacob
 */
@Service
public class HeroService {

    SuperDao superDao;

    LocationDao locationDao;

    OrganizationDao organizationDao;

    SightingDao sightingDao;

    @Autowired
    public HeroService(
            SuperDao superDao,
            LocationDao locationDao,
            OrganizationDao organizationDao,
            SightingDao sightingDao
    ) {
        this.superDao = superDao;
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
    }
//    Response<List<Location>> getAllLocations(){
//        Response<List<Location>> toReturn = new Response<List<Location>>
//                toReturn
//    }

    public DisplaySupersResponse getAllSupers() {
        DisplaySupersResponse response = new DisplaySupersResponse();
        try {
            List<Super> allSupers = superDao.getAllSupers();
            response.setAllSupers(allSupers);
            response.setSuccess(true);

        } catch (SuperPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public DisplayLocationsResponse getAllLocations() {
        DisplayLocationsResponse response = new DisplayLocationsResponse();
        List<Location> allLocations = null;
        try {
            allLocations = locationDao.getAllLocations();
            response.setAllLocations(allLocations);
            response.setSuccess(true);
        } catch (LocationPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public DisplayOrganizationsResponse getAllOrganizations() {
        DisplayOrganizationsResponse response = new DisplayOrganizationsResponse();
        try {
            List<Organization> allOrganizations = organizationDao.getAllOrganizations();
            response.setAllOrganizations(allOrganizations);
            response.setSuccess(true);
        } catch (OrganizationPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public DisplaySightingsResponse getAllSightings() {
        DisplaySightingsResponse response = new DisplaySightingsResponse();
        try {
            List<Sighting> allSightings = sightingDao.getAllSightings();
            response.setAllSightings(allSightings);
            response.setSuccess(true);

        } catch (SightingPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public LocationDetailsResponse getLocationDetails(Integer id) {
        LocationDetailsResponse response = new LocationDetailsResponse();
        try {
            Location location = locationDao.getLocationByID(id);
            List<Sighting> allSightings = sightingDao.getAllSightingsForLocation(id); // superId is null...
            for (Sighting toBuild : allSightings) {
                List<Super> sightedSupers = superDao.getAllSupersForSighting(toBuild.getSightingID());
                toBuild.setAllSupers(sightedSupers);
            }
            location.setAllSightings(allSightings);
            response.setLocation(location);
            response.setSuccess(true);

        } catch (LocationPersistenceException | SightingPersistenceException | SuperPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);

        }
        return response;
    }

    public SuperDetailsResponse getSuperDetails(Integer id) {
        SuperDetailsResponse response = new SuperDetailsResponse();

        try {
            Super singleSuper = superDao.getSuperByID(id);
            List<Organization> allOrganizations = organizationDao.getAllOrganizationsForSuper(id);
//        Organization singleOrganization = organizationDao.getOrganizationForSuper(id);
            List<Sighting> allSightings = sightingDao.getAllSightingsForSuper(id);
            for (Sighting toBuild : allSightings) {
                Location singleLocation = locationDao.getLocationByID(toBuild.getLocationID());
                toBuild.setSingleLocation(singleLocation);

            }
            singleSuper.setAllSightings(allSightings);
            singleSuper.setAllOrganizations(allOrganizations);

            response.setSingleSuper(singleSuper);
            response.setSuccess(true);
        } catch (LocationPersistenceException | SuperPersistenceException
                | SightingPersistenceException | OrganizationPersistenceException ex) {

            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }

        return response;
    }

    public OrganizationDetailsResponse getOrganizationsDetails(Integer id) {
        OrganizationDetailsResponse response = new OrganizationDetailsResponse();
        try {
            Organization organization = organizationDao.getOrganizationByID(id);
            List<Super> allSupers = superDao.getAllSupersForOrganization(id);
            organization.setAllSupers(allSupers);
            response.setOrganization(organization);
            response.setSuccess(true);

        } catch (OrganizationPersistenceException | SuperPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public SightingDetailsResponse getSightingDetails(Integer id) {
        SightingDetailsResponse response = new SightingDetailsResponse();
        try {

            Sighting sighting = sightingDao.getSightingByID(id);
            List<Super> allSupers = superDao.getAllSupersForSighting(id);
            Location location = locationDao.getLocationForSighting(id);
            sighting.setAllSupers(allSupers);
            sighting.setSingleLocation(location);
            response.setSighting(sighting);
            response.setSuccess(true);

        } catch (LocationPersistenceException | SightingPersistenceException
                | SuperPersistenceException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public AddLocationResponse addLocation(Location toAdd) {
        //VALIDATE TO ADD
        AddLocationResponse response = new AddLocationResponse();
        try {
            validateName(toAdd.getLocationName());
            validateDescription(toAdd.getDescription());
            validateAddress(toAdd.getAddress());
            validateLatitude(toAdd.getLatitude());
            validateLongitude(toAdd.getLongitude());
            Location addedLocation = locationDao.addLocation(toAdd);
            response.setAddedLocation(addedLocation);
            response.setSuccess(true);

        } catch (LocationPersistenceException
                | InvalidNameException
                | InvalidDescriptionException
                | InvalidAddressException
                | InvalidLatitudeException
                | InvalidLongitudeException ex) {

            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public AddSuperResponse addSuper(Super toAdd) {
        AddSuperResponse response = new AddSuperResponse();
        try {
            validateName(toAdd.getSuperName());
            validateDescription(toAdd.getDescription());
            validateQuirk(toAdd.getQuirk());
            Super addedSuper = superDao.addSuper(toAdd);
            response.setAddedSuper(addedSuper);
            response.setSuccess(true);
        } catch (SuperPersistenceException
                | InvalidNameException
                | InvalidDescriptionException
                | InvalidQuirkException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public AddSightingResponse addSighting(Sighting toAdd) {
        AddSightingResponse response = new AddSightingResponse();

        try {
            validateDate(toAdd.getDate());
//            Location singleLocation = locationDao.getLocationByID(toAdd.getLocationID());
//            toAdd.setSingleLocation(singleLocation);
            Sighting addedSighting = sightingDao.addSighting(toAdd);
            response.setAddedSighting(addedSighting);
            response.setSuccess(true);
        } catch (SightingPersistenceException | InvalidDateException ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public AddOrganizationResponse addOrganization(Organization toAdd) {
        AddOrganizationResponse response = new AddOrganizationResponse();

        try {
            validateName(toAdd.getOrganizationName());
            validateDescription(toAdd.getDescription());
            validateAddress(toAdd.getAddress());
            validatePhoneNumber(toAdd.getPhoneNumber());
            validateEmailAddress(toAdd.getEmailAddress());
            Organization addedOrganization = organizationDao.addOrganization(toAdd);
            response.setAddedOrganization(addedOrganization);
            response.setSuccess(true);
        } catch (OrganizationPersistenceException | InvalidNameException | InvalidDescriptionException
                | InvalidAddressException | InvalidPhoneNumberException
                | InvalidEmailAddressException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    private void validateName(String locationName) throws InvalidNameException {
        if (locationName.equalsIgnoreCase("") || locationName.length() > 30) {
            throw new InvalidNameException(locationName + "is not a valid item name.");
        }
    }

    private void validateDescription(String description) throws InvalidDescriptionException {
        if (description.equalsIgnoreCase("") || description.length() > 50) {
            throw new InvalidDescriptionException(description + "is not a valid description.");
        }
    }

    private void validateAddress(String address) throws InvalidAddressException {
        if (address.equalsIgnoreCase("") || address.length() > 60) {
            throw new InvalidAddressException(address + "is not a valid address.");
        }
    }

    private void validateLatitude(Double latitude) throws InvalidLatitudeException { // work on these
        if (latitude.toString().equalsIgnoreCase("")
                || latitude.doubleValue() > 90 || latitude.doubleValue() < -90) {
            throw new InvalidLatitudeException(latitude + "is not a valid latitude.");
        }
    }

    private void validateLongitude(Double longitude) throws InvalidLongitudeException {
        if (longitude.toString().equalsIgnoreCase("")
                || longitude.doubleValue() > 180 || longitude.doubleValue() < -180) {
            throw new InvalidLongitudeException(longitude + "is not a valid longitude.");
        }
//        }
    }

    private void validateQuirk(String quirk) throws InvalidQuirkException {
        if (quirk.equalsIgnoreCase("") || quirk.length() > 40) {
            throw new InvalidQuirkException(quirk + "is not a valid quirk.");
        }
    }

    private void validateDate(LocalDate date) throws InvalidDateException {
        if (date.toString().equals("") || date.isAfter(LocalDate.now())) {
            throw new InvalidDateException(date + "is not a valid date.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        //null // make phoneNumber a string would be easier
        if (phoneNumber.equalsIgnoreCase("") || phoneNumber.length() > 10) {
            throw new InvalidPhoneNumberException(phoneNumber + "is not a valid phone number.");
        }
    }

    private void validateEmailAddress(String emailAddress) throws InvalidEmailAddressException {
        // make sure there's an @ symbol and that there's stuff before and after @
        if (emailAddress.equalsIgnoreCase("") || emailAddress.length() < 3 || !emailAddress.contains("@")) {
            throw new InvalidEmailAddressException(emailAddress + "is not a valid email address.");
        }
    }

    public GetLocationByIDResponse getLocationByID(int id) { // not sure if needed
        GetLocationByIDResponse response = new GetLocationByIDResponse();
        Location singleLocation = new Location();
        try {
            singleLocation = locationDao.getLocationByID(id);
            List<Sighting> allSightings = sightingDao.getAllSightingsForLocation(id);
            singleLocation.setAllSightings(allSightings);
            response.setSingleLocation(singleLocation);
            response.setSuccess(true);
        } catch (SightingPersistenceException | LocationPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public GetOrganizationByIDResponse getOrganizationByID(int id) {
        GetOrganizationByIDResponse response = new GetOrganizationByIDResponse();
        Organization singleOrganization = new Organization();
        try {
            singleOrganization = organizationDao.getOrganizationByID(id);

            List<Super> allSupers = superDao.getAllSupersForOrganization(id);
            singleOrganization.setAllSupers(allSupers);
            response.setSingleOrganization(singleOrganization);
            response.setSuccess(true);
        } catch (SuperPersistenceException | OrganizationPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public GetSuperByIDResponse getSuperByID(int superId) {
        GetSuperByIDResponse response = new GetSuperByIDResponse();
        Super singleSuper = new Super();
        try {
            singleSuper = superDao.getSuperByID(superId);
            List<Organization> allOrgs = organizationDao.getAllOrganizationsForSuper(superId);
            singleSuper.setAllOrganizations(allOrgs);
            List<Sighting> allSightings = sightingDao.getAllSightingsForSuper(superId);
            singleSuper.setAllSightings(allSightings);
            response.setSingleSuper(singleSuper);
            response.setSuccess(true);
        } catch (SightingPersistenceException | OrganizationPersistenceException | SuperPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public EditOrganizationResponse editOrg(Organization org) {
        EditOrganizationResponse response = new EditOrganizationResponse();
        try {
             validateName(org.getOrganizationName());
            validateDescription(org.getDescription());
            validateAddress(org.getAddress());
            validatePhoneNumber(org.getPhoneNumber());
            validateEmailAddress(org.getEmailAddress());
            Organization editedOrg = organizationDao.editOrg(org);
            response.setSuccess(true);
            response.setEditedOrg(editedOrg);
        } catch (InvalidNameException | InvalidDescriptionException |
                 InvalidAddressException | InvalidPhoneNumberException | 
                InvalidEmailAddressException | OrganizationPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DeleteLocationResponse deleteLocation(Integer id) {
        DeleteLocationResponse response = new DeleteLocationResponse();
        try {
            locationDao.deleteLocation(id);

            response.setSuccess(true);
        } catch (LocationPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DeleteOrganizationResponse deleteOrganization(Integer id) {
        DeleteOrganizationResponse response = new DeleteOrganizationResponse();
        try {
            organizationDao.deleteOrganization(id);

            response.setSuccess(true);
        } catch (OrganizationPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DeleteSightingResponse deleteSighting(Integer id) {
        DeleteSightingResponse response = new DeleteSightingResponse();
        try {
            sightingDao.deleteSighting(id);

            response.setSuccess(true);
        } catch (SightingPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public DeleteSuperResponse deleteSuper(Integer id) {
        DeleteSuperResponse response = new DeleteSuperResponse();
        try {
            superDao.deleteSuper(id);

            response.setSuccess(true);
        } catch (SuperPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public GetSightingByIDResponse getSightingByID(int sightingId) {
        GetSightingByIDResponse response = new GetSightingByIDResponse();
        Sighting singleSighting = new Sighting();
        try {
            singleSighting = sightingDao.getSightingByID(sightingId);

            List<Super> allSupers = superDao.getAllSupersForSighting(sightingId);
            singleSighting.setAllSupers(allSupers);
            response.setSingleSighting(singleSighting);
            response.setSuccess(true);
        } catch (SuperPersistenceException | SightingPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    public EditSuperResponse editSuper(Super sup) {
        EditSuperResponse response = new EditSuperResponse();
        try {
            validateName(sup.getSuperName());
            validateDescription(sup.getDescription());
            validateQuirk(sup.getQuirk());
            Super editedSuper = superDao.editSuper(sup);
            response.setSuccess(true);
            response.setEditedSuper(editedSuper);
        } catch (InvalidDescriptionException | InvalidQuirkException | InvalidNameException | SuperPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public EditLocationResponse editLocation(Location location) {
        EditLocationResponse response = new EditLocationResponse();
        try {
            validateName(location.getLocationName());
            validateDescription(location.getDescription());
            validateAddress(location.getAddress());
            validateLatitude(location.getLatitude());
            validateLongitude(location.getLongitude());
            Location editedLocation = locationDao.editLocation(location);
            response.setSuccess(true);
            response.setEditedLocation(editedLocation);
        } catch (LocationPersistenceException | InvalidNameException
                | InvalidDescriptionException
                | InvalidAddressException
                | InvalidLatitudeException
                | InvalidLongitudeException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }
        return response;
    }

    public EditSightingResponse editSighting(Sighting sighting) {
        EditSightingResponse response = new EditSightingResponse();
        try {
            validateDate(sighting.getDate());
            Sighting editedSighting = sightingDao.editSighting(sighting);
            response.setSuccess(true);
            response.setEditedSighting(editedSighting);
        } catch (InvalidDateException | SightingPersistenceException ex) {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }
        return response;
    }

}

//}

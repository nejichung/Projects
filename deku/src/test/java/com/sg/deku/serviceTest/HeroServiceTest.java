/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.serviceTest;

import com.sg.deku.daoTest.AlwaysFailLocationDao;
import com.sg.deku.daoTest.AlwaysFailOrganizationDao;
import com.sg.deku.daoTest.AlwaysFailSightingDao;
import com.sg.deku.daoTest.AlwaysFailSuperDao;
import com.sg.deku.daoTest.InMemoryAllDao;
import com.sg.deku.models.Location;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import com.sg.deku.services.HeroService;
import com.sg.deku.services.responses.AddLocationResponse;
import com.sg.deku.services.responses.AddOrganizationResponse;
import com.sg.deku.services.responses.AddSightingResponse;
import com.sg.deku.services.responses.AddSuperResponse;
import com.sg.deku.services.responses.DeleteLocationResponse;
import com.sg.deku.services.responses.DeleteOrganizationResponse;
import com.sg.deku.services.responses.DeleteSightingResponse;
import com.sg.deku.services.responses.DeleteSuperResponse;
import com.sg.deku.services.responses.DisplayLocationsResponse;
import com.sg.deku.services.responses.DisplayOrganizationsResponse;
import com.sg.deku.services.responses.DisplaySightingsResponse;
import com.sg.deku.services.responses.DisplaySupersResponse;
import com.sg.deku.services.responses.EditLocationResponse;
import com.sg.deku.services.responses.EditOrganizationResponse;
import com.sg.deku.services.responses.EditSightingResponse;
import com.sg.deku.services.responses.EditSuperResponse;
import com.sg.deku.services.responses.GetLocationByIDResponse;
import com.sg.deku.services.responses.GetOrganizationByIDResponse;
import com.sg.deku.services.responses.GetSightingByIDResponse;
import com.sg.deku.services.responses.GetSuperByIDResponse;
import com.sg.deku.services.responses.LocationDetailsResponse;
import com.sg.deku.services.responses.OrganizationDetailsResponse;
import com.sg.deku.services.responses.SightingDetailsResponse;
import com.sg.deku.services.responses.SuperDetailsResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Software Guld
 */
public class HeroServiceTest {

    public HeroServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAllSupersSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        Assert.assertTrue(response.getSuccess());

        List<Super> allSupers = response.getAllSupers();
        Assert.assertEquals(1, allSupers.size());

        Super testSuper = allSupers.get(0);
        Assert.assertEquals("All Might", testSuper.getSuperName());
        Assert.assertEquals("#1 Hero", testSuper.getDescription());
        Assert.assertEquals("One For All", testSuper.getQuirk());
    }

    @Test
    public void getAllSupersSuperDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getAllLocationsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplayLocationsResponse response = service.getAllLocations();
        Assert.assertTrue(response.getSuccess());

        List<Location> allLocations = response.getAllLocations();
        Assert.assertEquals(1, allLocations.size());

        Location testLoc = allLocations.get(0);
        Assert.assertEquals("Konoha", testLoc.getLocationName());
        Assert.assertEquals("Pain", testLoc.getDescription());
        Assert.assertEquals("123 Hidden Leaf Village", testLoc.getAddress());
        Assert.assertEquals(23.5, testLoc.getLatitude(), 0.000001);
        Assert.assertEquals(40.7, testLoc.getLongitude(), 0.000001);

    }

    @Test
    public void getAllLocationsLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        DisplayLocationsResponse response = service.getAllLocations();
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getAllOrganizationsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplayOrganizationsResponse response = service.getAllOrganizations();
        Assert.assertTrue(response.getSuccess());

        List<Organization> allOrganizations = response.getAllOrganizations();
        Assert.assertEquals(1, allOrganizations.size());

        Organization testOrg = allOrganizations.get(0);
        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Infinite Tsukuyomi", testOrg.getDescription());
        Assert.assertEquals("That one cave", testOrg.getAddress());
        Assert.assertEquals("8901234569", testOrg.getPhoneNumber());
        Assert.assertEquals("knowtruepain@rain.com", testOrg.getEmailAddress());

    }

    @Test
    public void getAllOrgsOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        DisplayOrganizationsResponse response = service.getAllOrganizations();
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getAllSightingsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySightingsResponse response = service.getAllSightings();
        Assert.assertTrue(response.getSuccess());

        List<Sighting> allSightings = response.getAllSightings();
        Assert.assertEquals(1, allSightings.size());

        Sighting testSighting = allSightings.get(0);
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSighting.getDate());
    }

    @Test
    public void getAllSightingsSightingDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        DisplaySightingsResponse response = service.getAllSightings();
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getLocationDetailsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        LocationDetailsResponse response = service.getLocationDetails(1);
        Assert.assertTrue(response.getSuccess());

        Location testLoc = response.getLocation();
        Assert.assertEquals("Konoha", testLoc.getLocationName());
        Assert.assertEquals("Pain", testLoc.getDescription());
        Assert.assertEquals("123 Hidden Leaf Village", testLoc.getAddress());
        Assert.assertEquals(23.5, testLoc.getLatitude(), 0.000001);
        Assert.assertEquals(40.7, testLoc.getLongitude(), 0.000001);

        Assert.assertEquals(1, testLoc.getAllSightings().size());
        Sighting testSig = testLoc.getAllSightings().get(0);
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());

        List<Super> allSupers = testSig.getAllSupers();
        Super testSup = allSupers.get(0); // don't know if this sighting has a super on it
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());

    }

    @Test
    public void getLocationDetailsLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        LocationDetailsResponse response = service.getLocationDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getLocationDetailsSightingDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        LocationDetailsResponse response = service.getLocationDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getLocationDetailsSuperDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        LocationDetailsResponse response = service.getLocationDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperDetailsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        SuperDetailsResponse response = service.getSuperDetails(1);
        Assert.assertTrue(response.getSuccess());

        Super testSup = response.getSingleSuper();
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());

        Assert.assertEquals(1, testSup.getAllSightings().size());
        Sighting testSig = testSup.getAllSightings().get(0);
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());

        List<Organization> allOrgs = testSup.getAllOrganizations();
        Assert.assertEquals(1, allOrgs.size());
        Organization testOrg = allOrgs.get(0);
        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Infinite Tsukuyomi", testOrg.getDescription());
        Assert.assertEquals("That one cave", testOrg.getAddress());
        Assert.assertEquals("8901234569", testOrg.getPhoneNumber());
        Assert.assertEquals("knowtruepain@rain.com", testOrg.getEmailAddress());
    }

    @Test
    public void getSuperDetailsSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        SuperDetailsResponse response = service.getSuperDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperDetailsOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        SuperDetailsResponse response = service.getSuperDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperDetailsSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        SuperDetailsResponse response = service.getSuperDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperDetailsLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        SuperDetailsResponse response = service.getSuperDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getOrganizationsDetailsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        OrganizationDetailsResponse response = service.getOrganizationsDetails(1);
        Assert.assertTrue(response.getSuccess());

        Organization testOrg = response.getOrganization();
        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Infinite Tsukuyomi", testOrg.getDescription());
        Assert.assertEquals("That one cave", testOrg.getAddress());
        Assert.assertEquals("8901234569", testOrg.getPhoneNumber());
        Assert.assertEquals("knowtruepain@rain.com", testOrg.getEmailAddress());

        List<Super> allSupers = testOrg.getAllSupers();
        Super testSup = allSupers.get(0);
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());
    }

    @Test
    public void getOrgDetailsOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        OrganizationDetailsResponse response = service.getOrganizationsDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getOrgDetailsSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        OrganizationDetailsResponse response = service.getOrganizationsDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSightingDetailsSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        SightingDetailsResponse response = service.getSightingDetails(1);
        Assert.assertTrue(response.getSuccess());

        Sighting testSig = response.getSighting();
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());

        List<Super> allSupers = testSig.getAllSupers();
        Super testSup = allSupers.get(0);
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());

        Location testLoc = testSig.getSingleLocation();
        Assert.assertEquals("Konoha", testLoc.getLocationName());
        Assert.assertEquals("Pain", testLoc.getDescription());
        Assert.assertEquals("123 Hidden Leaf Village", testLoc.getAddress());
        Assert.assertEquals(23.5, testLoc.getLatitude(), 0.000001);
        Assert.assertEquals(40.7, testLoc.getLongitude(), 0.000001);
    }

    @Test
    public void getSightingDetailsSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        SightingDetailsResponse response = service.getSightingDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSightingDetailsSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        SightingDetailsResponse response = service.getSightingDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSightingDetailsLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        SightingDetailsResponse response = service.getSightingDetails(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("cool");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertTrue(response.getSuccess());

        DisplayLocationsResponse response2 = service.getAllLocations();
        Assert.assertEquals(2, response2.getAllLocations().size());

        Location testLoc = response2.getAllLocations().get(1);
        Assert.assertEquals("The Software Guild", testLoc.getLocationName());
        Assert.assertEquals("cool", testLoc.getDescription());
        Assert.assertEquals("12446 ldkfajk ave", testLoc.getAddress());
        Assert.assertEquals(69.0, testLoc.getLatitude(), 0.000001);
        Assert.assertEquals(69.0, testLoc.getLongitude(), 0.000001);

    }

    @Test
    public void addLocationLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("cool");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationInvalidName() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("");
        newLoc.setDescription("cool");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationInvalidDesc() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationInvalidAddresss() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("cool");
        newLoc.setAddress("");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationInvalidLat() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("cool");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(20000.0);
        newLoc.setLongitude(69.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addLocationInvalidLong() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Location newLoc = new Location();
        newLoc.setLocationName("The Software Guild");
        newLoc.setDescription("cool");
        newLoc.setAddress("12446 ldkfajk ave");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(20000.0);
        AddLocationResponse response = service.addLocation(newLoc);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addSuperSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("SuperMan");
        newSup.setDescription("Invincible");
        newSup.setQuirk("Invincible");
        AddSuperResponse response = service.addSuper(newSup);
        Assert.assertTrue(response.getSuccess());

        DisplaySupersResponse response2 = service.getAllSupers();
        Assert.assertEquals(2, response2.getAllSupers().size());

        Super testSup = response2.getAllSupers().get(1);
        Assert.assertEquals("SuperMan", testSup.getSuperName());
        Assert.assertEquals("Invincible", testSup.getDescription());
        Assert.assertEquals("Invincible", testSup.getQuirk());

    }

    @Test
    public void addSuperSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("SuperMan");
        newSup.setDescription("Invincible");
        newSup.setQuirk("Invincible");
        AddSuperResponse response = service.addSuper(newSup);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addSuperInvalidName() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("");
        newSup.setDescription("Invincible");
        newSup.setQuirk("Invincible");
        AddSuperResponse response = service.addSuper(newSup);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addSuperInvalidDesc() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("SuperMan");
        newSup.setDescription("");
        newSup.setQuirk("Invincible");
        AddSuperResponse response = service.addSuper(newSup);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addSuperInvalidQuirk() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("SuperMan");
        newSup.setDescription("Invincible");
        newSup.setQuirk("");
        AddSuperResponse response = service.addSuper(newSup);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addSightingSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2012, 2, 2));
        AddSightingResponse response = service.addSighting(newSig);
        Assert.assertTrue(response.getSuccess());

        DisplaySightingsResponse response2 = service.getAllSightings();
        Assert.assertEquals(2, response2.getAllSightings().size());

        Sighting testSig = response2.getAllSightings().get(1);
        Assert.assertEquals(LocalDate.of(2012, 2, 2), testSig.getDate());

    }

    @Test
    public void addSightingSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2012, 2, 2));
        AddSightingResponse response = service.addSighting(newSig);
        Assert.assertFalse(response.getSuccess());

    }

    @Test
    public void addSightingInvalidDate() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2090, 2, 2));
        AddSightingResponse response = service.addSighting(newSig);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void addOrganizationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("Avengers");
        newOrg.setDescription("EndGame");
        newOrg.setAddress("Stark Tower");
        newOrg.setPhoneNumber("108983874");
        newOrg.setEmailAddress("alkslajdksf@avengers.com");
        AddOrganizationResponse response = service.addOrganization(newOrg);
        Assert.assertTrue(response.getSuccess());

        DisplayOrganizationsResponse response2 = service.getAllOrganizations();
        Assert.assertEquals(2, response2.getAllOrganizations().size());

        Organization testOrg = response2.getAllOrganizations().get(1);
        Assert.assertEquals("Avengers", testOrg.getOrganizationName());
        Assert.assertEquals("EndGame", testOrg.getDescription());
        Assert.assertEquals("Stark Tower", testOrg.getAddress());
        Assert.assertEquals("108983874", testOrg.getPhoneNumber());
        Assert.assertEquals("alkslajdksf@avengers.com", testOrg.getEmailAddress());
    }

    @Test
    public void getLocationByIDSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        GetLocationByIDResponse response = service.getLocationByID(1);
        Assert.assertTrue(response.getSuccess());

        Location testLoc = response.getSingleLocation();
        Assert.assertEquals("Konoha", testLoc.getLocationName());
        Assert.assertEquals("Pain", testLoc.getDescription());
        Assert.assertEquals("123 Hidden Leaf Village", testLoc.getAddress());
        Assert.assertEquals(23.5, testLoc.getLatitude(), 0.000001);
        Assert.assertEquals(40.7, testLoc.getLongitude(), 0.000001);

        Sighting testSig = testLoc.getAllSightings().get(0);
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());
    }

    @Test
    public void getLocationByIDLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        GetLocationByIDResponse response = service.getLocationByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getLocationByIDSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        GetLocationByIDResponse response = service.getLocationByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getOrganizationByIDSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        GetOrganizationByIDResponse response = service.getOrganizationByID(1);
        Assert.assertTrue(response.getSuccess());

        Organization testOrg = response.getSingleOrganization();
        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Infinite Tsukuyomi", testOrg.getDescription());
        Assert.assertEquals("That one cave", testOrg.getAddress());
        Assert.assertEquals("8901234569", testOrg.getPhoneNumber());
        Assert.assertEquals("knowtruepain@rain.com", testOrg.getEmailAddress());

        List<Super> allSupers = testOrg.getAllSupers();
        Super testSup = allSupers.get(0);
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());

    }

    @Test
    public void getOrgByIDOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        GetOrganizationByIDResponse response = service.getOrganizationByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getOrgByIDSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        GetOrganizationByIDResponse response = service.getOrganizationByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperByIDSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        GetSuperByIDResponse response = service.getSuperByID(1);
        Assert.assertTrue(response.getSuccess());

        Super testSup = response.getSingleSuper();
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());

        Assert.assertEquals(1, testSup.getAllSightings().size());
        Sighting testSig = testSup.getAllSightings().get(0);
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());

        List<Organization> allOrgs = testSup.getAllOrganizations();
        Assert.assertEquals(1, allOrgs.size());
        Organization testOrg = allOrgs.get(0);
        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Infinite Tsukuyomi", testOrg.getDescription());
        Assert.assertEquals("That one cave", testOrg.getAddress());
        Assert.assertEquals("8901234569", testOrg.getPhoneNumber());
        Assert.assertEquals("knowtruepain@rain.com", testOrg.getEmailAddress());

    }

    @Test
    public void getSuperByIDSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        GetSuperByIDResponse response = service.getSuperByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperByIDOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        GetSuperByIDResponse response = service.getSuperByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSuperByIDSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        GetSuperByIDResponse response = service.getSuperByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSightingByIDSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        GetSightingByIDResponse response = service.getSightingByID(1);
        Assert.assertTrue(response.getSuccess());

        Sighting testSig = response.getSingleSighting();
        Assert.assertEquals(LocalDate.of(2010, 2, 2), testSig.getDate());

        List<Super> allSupers = testSig.getAllSupers();
        Super testSup = allSupers.get(0);
        Assert.assertEquals("All Might", testSup.getSuperName());
        Assert.assertEquals("#1 Hero", testSup.getDescription());
        Assert.assertEquals("One For All", testSup.getQuirk());
    }

    @Test
    public void getSightingByIDSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        GetSightingByIDResponse response = service.getSightingByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void getSightingByIDSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        GetSightingByIDResponse response = service.getSightingByID(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void deleteLocationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response2 = service.getAllLocations();
        List<Location> allLocsBefore = response2.getAllLocations();
        Assert.assertEquals(1, allLocsBefore.size());

        DeleteLocationResponse response = service.deleteLocation(1);
        Assert.assertTrue(response.getSuccess());

        DisplayLocationsResponse response3 = service.getAllLocations();
        List<Location> allLocsAfter = response3.getAllLocations();
        Assert.assertEquals(0, allLocsAfter.size());

    }

    @Test
    public void deleteLocLocDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        DeleteLocationResponse response = service.deleteLocation(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void deleteOrganizationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response2 = service.getAllOrganizations();
        List<Organization> allOrgsBefore = response2.getAllOrganizations();
        Assert.assertEquals(1, allOrgsBefore.size());

        DeleteOrganizationResponse response = service.deleteOrganization(1);
        Assert.assertTrue(response.getSuccess());

        DisplayOrganizationsResponse response3 = service.getAllOrganizations();
        List<Organization> allOrgsAfter = response3.getAllOrganizations();
        Assert.assertEquals(0, allOrgsAfter.size());
    }

    @Test
    public void deleteOrgOrgDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        DeleteOrganizationResponse response = service.deleteOrganization(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void deleteSuperSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplaySupersResponse response2 = service.getAllSupers();
        List<Super> allSupsBefore = response2.getAllSupers();
        Assert.assertEquals(1, allSupsBefore.size());

        DeleteSuperResponse response = service.deleteSuper(1);
        Assert.assertTrue(response.getSuccess());

        DisplaySupersResponse response3 = service.getAllSupers();
        List<Super> allSupsAfter = response3.getAllSupers();
        Assert.assertEquals(0, allSupsAfter.size());
    }

    @Test
    public void deleteSupSupDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        DeleteSuperResponse response = service.deleteSuper(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void deleteSightingSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplaySightingsResponse response2 = service.getAllSightings();
        List<Sighting> allSigsBefore = response2.getAllSightings();
        Assert.assertEquals(1, allSigsBefore.size());

        DeleteSightingResponse response = service.deleteSighting(1);
        Assert.assertTrue(response.getSuccess());

        DisplaySightingsResponse response3 = service.getAllSightings();
        List<Sighting> allSigsAfter = response3.getAllSightings();
        Assert.assertEquals(0, allSigsAfter.size());
    }

    @Test
    public void deleteSigSigDaoFail() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        DeleteSightingResponse response = service.deleteSighting(1);
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void editLocationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("jacob's");
        newLoc.setDescription("cool");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertTrue(response2.getSuccess());

        Location editedLoc = response2.getEditedLocation();
        Assert.assertEquals("jacob's", newLoc.getLocationName());
        Assert.assertEquals("cool", newLoc.getDescription());
        Assert.assertEquals("123565 big st", newLoc.getAddress());
        Assert.assertEquals(69.0, newLoc.getLatitude(), 0.000001);
        Assert.assertEquals(69.0, newLoc.getLongitude(), 0.000001);

        List<Sighting> editedSigs = editedLoc.getAllSightings();
        Sighting editedSig = editedSigs.get(0);
        Assert.assertEquals(LocalDate.of(2010, 1, 1), editedSig.getDate());
    }
    @Test
    public void editLocInvalidName(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("");
        newLoc.setDescription("cool");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editLocInvalidDescription(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("jacob's");
        newLoc.setDescription("");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editLocInvalidAddress(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("jacob's");
        newLoc.setDescription("cool");
        newLoc.setAddress("");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editLocInvalidLatitude(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("jacob's");
        newLoc.setDescription("cool");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(1000.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editLocInvalidLongitude(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayLocationsResponse response = service.getAllLocations();
        List<Location> allLocs = response.getAllLocations();
        Location newLoc = new Location();
        for (Location toCheck : allLocs) {
            if (toCheck.getLocationID() == 1) {
                newLoc = toCheck;
                break;
            }
        }

        newLoc.setLocationName("jacob's");
        newLoc.setDescription("cool");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(1000.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }
            
    @Test
    public void editLocLocDaoFail(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailLocationDao failDao = new AlwaysFailLocationDao();
        HeroService service = new HeroService(allDao, failDao, allDao, allDao);
        
        
//        DisplayLocationsResponse response = service.getAllLocations();
//        List<Location> allLocs = response.getAllLocations(); // null since that dao fails
//        Location newLoc = new Location();
//        for (Location toCheck : allLocs) {
//            if (toCheck.getLocationID() == 1) {
//                newLoc = toCheck;
//                break;
//            }
//        }
        Location newLoc = new Location();
        newLoc.setLocationName("jacob's");
        newLoc.setDescription("cool");
        newLoc.setAddress("123565 big st");
        newLoc.setLatitude(69.0);
        newLoc.setLongitude(69.0);

        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newLoc.setAllSightings(newSightings);

        EditLocationResponse response2 = service.editLocation(newLoc);
        Assert.assertFalse(response2.getSuccess());
    }

    @Test
    public void editOrganizationSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("team");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertTrue(response2.getSuccess());

        Organization editedOrg = response2.getEditedOrg();
        Assert.assertEquals("LEAGUE", editedOrg.getOrganizationName());
        Assert.assertEquals("team", editedOrg.getDescription());
        Assert.assertEquals("123534", editedOrg.getAddress());
        Assert.assertEquals("12345456", editedOrg.getPhoneNumber());
        Assert.assertEquals("alkfjlf@aksdjf@kasfdlk.com", editedOrg.getEmailAddress());

        List<Super> editedSups = editedOrg.getAllSupers();
        Super editedSup = editedSups.get(0);
        Assert.assertEquals("Spongebob", editedSup.getSuperName());
    }
    @Test
    public void editOrgInvalidName(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("");
        newOrg.setDescription("team");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editOrgInvalidDescription(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editOrgInvalidAddress(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("team");
        newOrg.setAddress("");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editOrgInvalidPhoneNumber(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("team");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editOrgInvalidEmailAddress(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);

        DisplayOrganizationsResponse response = service.getAllOrganizations();
        List<Organization> allOrgs = response.getAllOrganizations();
        Organization newOrg = new Organization();
        for (Organization toCheck : allOrgs) {
            if (toCheck.getOrganizationID() == 1) {
                newOrg = toCheck;
                break;
            }
        }
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("team");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editOrgOrgDaoFail(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailOrganizationDao failDao = new AlwaysFailOrganizationDao();
        HeroService service = new HeroService(allDao, allDao, failDao, allDao);
        Organization newOrg= new Organization();
        newOrg.setOrganizationName("LEAGUE");
        newOrg.setDescription("team");
        newOrg.setAddress("123534");
        newOrg.setPhoneNumber("12345456");
        newOrg.setEmailAddress("alkfjlf@aksdjf@kasfdlk.com");

        List<Super> newSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSups.add(newSup);
        newOrg.setAllSupers(newSups);

        EditOrganizationResponse response2 = service.editOrg(newOrg);
        Assert.assertFalse(response2.getSuccess());
    }

    @Test
    public void editSuperSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        List<Super> allSups = response.getAllSupers();
        Super newSup = new Super();
        for (Super toCheck : allSups) {
            if (toCheck.getSuperID() == 1) {
                newSup = toCheck;
                break;
            }
        }
        newSup.setSuperName("Spongebob");
        newSup.setDescription("square");
        newSup.setQuirk("burgers");
        
        List<Organization> newOrgs = new ArrayList<>();
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("krusty");
        newOrgs.add(newOrg);
        newSup.setAllOrganizations(newOrgs);
        
        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newSup.setAllSightings(newSightings);
        
        EditSuperResponse response2 = service.editSuper(newSup);
        Assert.assertTrue(response2.getSuccess());
        
        Super editedSup = response2.getEditedSuper();
        
        Assert.assertEquals("Spongebob", editedSup.getSuperName());
        Assert.assertEquals("square", editedSup.getDescription());
        Assert.assertEquals("burgers", editedSup.getQuirk());
        
        List<Organization> editedOrgs = editedSup.getAllOrganizations();
        Organization editedOrg = editedOrgs.get(0);
        Assert.assertEquals("krusty", editedOrg.getOrganizationName());
        
        List<Sighting> editedSigs = editedSup.getAllSightings();
        Sighting editedSig = editedSigs.get(0);
        Assert.assertEquals(LocalDate.of(2010, 1, 1), editedSig.getDate());
    }
    @Test
    public void editSuperInvalidName(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        List<Super> allSups = response.getAllSupers();
        Super newSup = new Super();
        for (Super toCheck : allSups) {
            if (toCheck.getSuperID() == 1) {
                newSup = toCheck;
                break;
            }
        }
        newSup.setSuperName("");
        newSup.setDescription("square");
        newSup.setQuirk("burgers");
        
        List<Organization> newOrgs = new ArrayList<>();
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("krusty");
        newOrgs.add(newOrg);
        newSup.setAllOrganizations(newOrgs);
        
        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newSup.setAllSightings(newSightings);
        
        EditSuperResponse response2 = service.editSuper(newSup);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editSuperInvalidDescription(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        List<Super> allSups = response.getAllSupers();
        Super newSup = new Super();
        for (Super toCheck : allSups) {
            if (toCheck.getSuperID() == 1) {
                newSup = toCheck;
                break;
            }
        }
        newSup.setSuperName("Spongebob");
        newSup.setDescription("");
        newSup.setQuirk("burgers");
        
        List<Organization> newOrgs = new ArrayList<>();
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("krusty");
        newOrgs.add(newOrg);
        newSup.setAllOrganizations(newOrgs);
        
        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newSup.setAllSightings(newSightings);
        
        EditSuperResponse response2 = service.editSuper(newSup);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editSuperInvalidQuirk(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySupersResponse response = service.getAllSupers();
        List<Super> allSups = response.getAllSupers();
        Super newSup = new Super();
        for (Super toCheck : allSups) {
            if (toCheck.getSuperID() == 1) {
                newSup = toCheck;
                break;
            }
        }
        newSup.setSuperName("Spongebob");
        newSup.setDescription("square");
        newSup.setQuirk("");
        
        List<Organization> newOrgs = new ArrayList<>();
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("krusty");
        newOrgs.add(newOrg);
        newSup.setAllOrganizations(newOrgs);
        
        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newSup.setAllSightings(newSightings);
        
        EditSuperResponse response2 = service.editSuper(newSup);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editSuperSupDaoFail(){
        InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSuperDao failDao = new AlwaysFailSuperDao();
        HeroService service = new HeroService(failDao, allDao, allDao, allDao);
        Super newSup = new Super();
        newSup.setSuperName("Spongebob");
        newSup.setDescription("square");
        newSup.setQuirk("burgers");
        
        List<Organization> newOrgs = new ArrayList<>();
        Organization newOrg = new Organization();
        newOrg.setOrganizationName("krusty");
        newOrgs.add(newOrg);
        newSup.setAllOrganizations(newOrgs);
        
        List<Sighting> newSightings = new ArrayList<>();
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        newSightings.add(newSig);
        newSup.setAllSightings(newSightings);
        
        EditSuperResponse response2 = service.editSuper(newSup);
        Assert.assertFalse(response2.getSuccess());
    }

    @Test
    public void editSightingSuccess() {
        InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySightingsResponse response = service.getAllSightings();
        List<Sighting> allSightings = response.getAllSightings();
        Sighting newSig = new Sighting();
        for (Sighting toCheck : allSightings) {
            if (toCheck.getSightingID() == 1) {
                newSig = toCheck;
                break;
            }
        }
        newSig.setDate(LocalDate.of(2010, 1, 1));
        
        Location newLoc = new Location();
        newLoc.setLocationName("hehe");
        newSig.setSingleLocation(newLoc);
        
        List<Super> allSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("patrick");
        allSups.add(newSup);
        newSig.setAllSupers(allSups);
        
        EditSightingResponse response2 = service.editSighting(newSig);
        Assert.assertTrue(response2.getSuccess());
        
        Sighting editedSig = response2.getEditedSighting();
        Assert.assertEquals(LocalDate.of(2010, 1, 1), editedSig.getDate());
        Assert.assertEquals("hehe", editedSig.getSingleLocation().getLocationName());
        
        List<Super> editedSups = editedSig.getAllSupers();
        Super editedSup = editedSups.get(0);
        Assert.assertEquals("patrick", editedSup.getSuperName());
                
    }
    @Test
    public void editSightingInvalidDate(){
         InMemoryAllDao allDao = new InMemoryAllDao();
        HeroService service = new HeroService(allDao, allDao, allDao, allDao);
        DisplaySightingsResponse response = service.getAllSightings();
        List<Sighting> allSightings = response.getAllSightings();
        Sighting newSig = new Sighting();
        for (Sighting toCheck : allSightings) {
            if (toCheck.getSightingID() == 1) {
                newSig = toCheck;
                break;
            }
        }
        newSig.setDate(LocalDate.of(2030, 1, 1));
        
        Location newLoc = new Location();
        newLoc.setLocationName("hehe");
        newSig.setSingleLocation(newLoc);
        
        List<Super> allSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("patrick");
        allSups.add(newSup);
        newSig.setAllSupers(allSups);
        
        EditSightingResponse response2 = service.editSighting(newSig);
        Assert.assertFalse(response2.getSuccess());
    }
    @Test
    public void editSightingSigDaoFail(){
         InMemoryAllDao allDao = new InMemoryAllDao();
        AlwaysFailSightingDao failDao = new AlwaysFailSightingDao();
        HeroService service = new HeroService(allDao, allDao, allDao, failDao);
        Sighting newSig = new Sighting();
        newSig.setDate(LocalDate.of(2010, 1, 1));
        
        Location newLoc = new Location();
        newLoc.setLocationName("hehe");
        newSig.setSingleLocation(newLoc);
        
        List<Super> allSups = new ArrayList<>();
        Super newSup = new Super();
        newSup.setSuperName("patrick");
        allSups.add(newSup);
        newSig.setAllSupers(allSups);
        
        EditSightingResponse response2 = service.editSighting(newSig);
        Assert.assertFalse(response2.getSuccess());
    }
}

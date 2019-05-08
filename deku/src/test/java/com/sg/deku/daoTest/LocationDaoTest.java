/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.deku.daos.LocationDao;
import com.sg.deku.daos.LocationDaoDB;
import com.sg.deku.daos.LocationPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Jacob
 */
public class LocationDaoTest {

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception { //set up ???
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("heroTest");
        ds.setUser("root");
        ds.setPassword("Interestaeng7!");
        ds.setServerTimezone("America/Chicago");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        jdbc = new JdbcTemplate(ds);

        jdbc.update("DELETE FROM SupersOrganizations");
        jdbc.update("DELETE FROM SupersSightings");
        jdbc.update("DELETE FROM Sightings");
        jdbc.update("DELETE FROM Supers");
        jdbc.update("DELETE FROM Organizations");
        jdbc.update("DELETE FROM Locations");

        jdbc.update("INSERT INTO Supers(SuperID, `Name`, `Description`, Quirk)\n"
                + "VALUES\n"
                + "('1','Deku','Methodical, Timid, and Quick Thinking','One For All')");
        jdbc.update("INSERT INTO Organizations(OrganizationID, `Name`, `Description`, Address, PhoneNumber, EmailAddress)\n"
                + "VALUES\n"
                + "('1','UA','#1 Academia','123 Yolo Street, Deez 42000','1234567890','allmight@ua.com')");

        jdbc.update("INSERT INTO Locations(LocationID, LocationName, `Description`, Address, Latitude, Longitude)\n"
                + "VALUES\n"
                + "('1','Forest of Death','Very cool','123 Yolo Street, Deez 42000','57.75','-21.23')");

        jdbc.update("INSERT INTO Sightings (SightingID, `Date`, LocationID)\n"
                + "VALUES\n"
                + "('1','2020-02-02','1')");

        jdbc.update("INSERT INTO SupersOrganizations (SuperID, OrganizationID)\n"
                + "VALUES\n"
                + "('1','1')");

        jdbc.update("INSERT INTO SupersSightings (SuperID, SightingID)\n"
                + "VALUES\n"
                + "('1','1')");

    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void getAllLocations() throws LocationPersistenceException {
        LocationDao locationDao = new LocationDaoDB(jdbc);
        List<Location> allLocations = locationDao.getAllLocations();
        Assert.assertNotNull(allLocations);
        
        Location toLocation = allLocations.get(0);
        
        Assert.assertEquals("Forest of Death", toLocation.getLocationName());
        Assert.assertEquals("Very cool", toLocation.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", toLocation.getAddress());
        Assert.assertEquals(57.75, toLocation.getLatitude(), 0.00001);
        Assert.assertEquals(-21.23, toLocation.getLongitude(), 0.00001);
    }
    
    @Test
    public void getLocationByID() throws LocationPersistenceException {
       LocationDao locationDao = new LocationDaoDB(jdbc);
       Location forest = locationDao.getLocationByID(1);
        Assert.assertNotNull(forest);
        
        Assert.assertEquals("Forest of Death", forest.getLocationName());
        Assert.assertEquals("Very cool", forest.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", forest.getAddress());
        Assert.assertEquals(57.75, forest.getLatitude(), 0.00001);
        Assert.assertEquals(-21.23, forest.getLongitude(), 0.00001);
    }
    
    @Test
    public void getLocationForSighting () throws LocationPersistenceException{
        LocationDao locationDao = new LocationDaoDB(jdbc);
        Sighting newSig = new Sighting();
        newSig.setSightingID(1);
        Location loc = locationDao.getLocationForSighting(newSig.getSightingID());
        Assert.assertEquals("Forest of Death", loc.getLocationName());
        Assert.assertEquals("Very cool", loc.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", loc.getAddress());
        Assert.assertEquals(57.75, loc.getLatitude(), 0.00001);
        Assert.assertEquals(-21.23, loc.getLongitude(), 0.00001);
    }
    
    @Test
    public void addLocation() throws LocationPersistenceException {
        LocationDao locationDao = new LocationDaoDB(jdbc);
        List<Location> allLocations = locationDao.getAllLocations();
        Location toAdd = new Location();
        toAdd.setLocationID(2);
        toAdd.setLocationName("champlin");
        toAdd.setDescription("dank af");
        toAdd.setAddress("my house");
        toAdd.setLatitude(60.0);
        toAdd.setLongitude(60.0);
        Location testLocation = locationDao.addLocation(toAdd);
        
        Assert.assertEquals("champlin", testLocation.getLocationName());
        Assert.assertEquals("dank af", testLocation.getDescription());
        Assert.assertEquals("my house", testLocation.getAddress());
        Assert.assertEquals(60.0, testLocation.getLatitude(), 0.00001);
        Assert.assertEquals(60.0, testLocation.getLongitude(), 0.00001);
    }
    @Test
     public void deleteLocation() throws LocationPersistenceException{
         LocationDao locationDao = new LocationDaoDB(jdbc);
         List<Location> allLocationsBefore = locationDao.getAllLocations();
         Assert.assertEquals(1, allLocationsBefore.size());
         
         locationDao.deleteLocation(1);
         List<Location> allLocationsAfter = locationDao.getAllLocations();
         Assert.assertEquals(0, allLocationsAfter.size());
         
         
     }
     @Test
     public void editLocation() throws LocationPersistenceException{
         LocationDao locationDao = new LocationDaoDB(jdbc);
         List<Location> allLocations = locationDao.getAllLocations();
         Location editLocation = allLocations.get(0);
         
         editLocation.setLocationName("bikini bottom");
         editLocation.setDescription("bottom");
         editLocation.setAddress("bottom");
         editLocation.setLatitude(69.0);
         editLocation.setLongitude(69.0);
         
         Location editedLocation = locationDao.editLocation(editLocation);
         Assert.assertEquals("bikini bottom", editedLocation.getLocationName());
         Assert.assertEquals("bottom", editedLocation.getDescription());
         Assert.assertEquals("bottom", editedLocation.getAddress());
         Assert.assertEquals(69.0, editedLocation.getLatitude(), 0.000001);
         Assert.assertEquals(69.0, editedLocation.getLongitude(), 0.000001);
     }
}

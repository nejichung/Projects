/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.deku.daos.SightingDao;
import com.sg.deku.daos.SightingDaoDB;
import com.sg.deku.daos.SightingPersistenceException;
import com.sg.deku.models.Location;
import com.sg.deku.models.Sighting;
import com.sg.deku.models.Super;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
public class SightingDaoTest {

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
                + "('1','2000-02-02','1')");

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
    public void getAllSightings() throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        List<Sighting> allSightings = sightingDao.getAllSightings();
        Assert.assertNotNull(allSightings);

        Sighting toSighting = allSightings.get(0);
//        Assert.assertEquals("1", toSighting.getSightingID());
        Assert.assertEquals(LocalDate.of(2000, 2, 2), toSighting.getDate());
        Assert.assertEquals(1, toSighting.getLocationID().intValue());
    }

    @Test
    public void getSightingByID() throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        Sighting singleSighting = sightingDao.getSightingByID(1);
        Assert.assertNotNull(singleSighting);

        Assert.assertEquals(LocalDate.of(2000, 2, 2), singleSighting.getDate());
        Assert.assertEquals(1, singleSighting.getLocationID().intValue());
    }

    @Test
    public void getSightingsForSuper() throws SightingPersistenceException { // refer to the SuperDaoTest and confirm that's correct before doig these
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        Super newSup = new Super();
        newSup.setSuperID(1);
        List<Sighting> allSigs = sightingDao.getAllSightingsForSuper(newSup.getSuperID());
        Sighting sig = allSigs.get(0);
        Assert.assertEquals(LocalDate.of(2000, 2, 2), sig.getDate());
    }

    @Test
    public void getSightingsForLocation() throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        Location newLoc = new Location();
        newLoc.setLocationID(1);
        List<Sighting> allSigs = sightingDao.getAllSightingsForSuper(newLoc.getLocationID());
        Sighting sig = allSigs.get(0);
        Assert.assertEquals(LocalDate.of(2000, 2, 2), sig.getDate());
    }

    @Test
    public void addSighting() throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        List<Sighting> allSightings = sightingDao.getAllSightings();
        Sighting toAdd = new Sighting();
        toAdd.setDate(LocalDate.of(2010, 07, 07));
//        Location loc = new Location();
//        loc.setLocationName("woah");
//        loc.setLocationID(1);
//        toAdd.setSingleLocation(loc);
        toAdd.setLocationID(1);

        List<Super> allSups = new ArrayList<>();
        Super sup = new Super();
        sup.setSuperName("deku");
        sup.setSuperID(1);
        allSups.add(sup);
        toAdd.setAllSupers(allSups);

        Sighting testSighting = sightingDao.addSighting(toAdd);

        Assert.assertEquals(LocalDate.of(2010, 07, 07), testSighting.getDate());
        Assert.assertEquals(1, testSighting.getLocationID().intValue());

    }

    @Test
    public void deleteSighting() throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        List<Sighting> allSightingsBefore = sightingDao.getAllSightings();
        Assert.assertEquals(1, allSightingsBefore.size());

        sightingDao.deleteSighting(1);
        List<Sighting> allSightingsAfter = sightingDao.getAllSightings();
        Assert.assertEquals(0, allSightingsAfter.size());
    }

    public void editSighting(Sighting sighting) throws SightingPersistenceException {
        SightingDao sightingDao = new SightingDaoDB(jdbc);
        List<Sighting> allSigs = sightingDao.getAllSightings();
        Sighting sig = allSigs.get(0);
        sig.setDate(LocalDate.of(1997, 7, 7));
        
        List<Super> allSups = new ArrayList<>();
        Super sup = new Super();
        sup.setSuperName("deku");
        sup.setSuperID(1);
        allSups.add(sup);
        sig.setAllSupers(allSups);
        
        Sighting editedSig = sightingDao.editSighting(sig);
        Assert.assertEquals(LocalDate.of(1997, 07, 07), editedSig.getDate());
        
        List<Super> editedSups = editedSig.getAllSupers();
        Super editedSup = editedSups.get(0);
        Assert.assertEquals("deku", editedSup.getSuperName());
    }
}

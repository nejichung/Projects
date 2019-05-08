/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.deku.daos.SuperDao;
import com.sg.deku.daos.SuperDaoDB;
import com.sg.deku.daos.SuperPersistenceException;
import com.sg.deku.models.Organization;
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
public class SuperDaoTest {

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
    public void getAllSupers() throws SuperPersistenceException {
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupers = superDao.getAllSupers(); // null pointer
        Assert.assertNotNull(allSupers);

        Super toSuper = allSupers.get(0);
//        Assert.assertEquals(1, toSuper.getSuperID());
        Assert.assertEquals("Deku", toSuper.getSuperName());
        Assert.assertEquals("Methodical, Timid, and Quick Thinking", toSuper.getDescription());
        Assert.assertEquals("One For All", toSuper.getQuirk());

    }

    @Test
    public void getSuperByID() throws SuperPersistenceException {
        SuperDao superDao = new SuperDaoDB(jdbc);
        Super deku = superDao.getSuperByID(1); // null whenever i call to my superDao
        Assert.assertNotNull(deku);

        Assert.assertEquals("Deku", deku.getSuperName());
        Assert.assertEquals("Methodical, Timid, and Quick Thinking", deku.getDescription());
        Assert.assertEquals("One For All", deku.getQuirk());

    }

    @Test
    public void getSupersForOrganization() throws SuperPersistenceException { // need to check if this is right
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupers = superDao.getAllSupersForOrganization(1); // don't know if this is correct
        Assert.assertNotNull(allSupers);

        Super singleSuper = allSupers.get(0);
        Assert.assertEquals("Deku", singleSuper.getSuperName());
        Assert.assertEquals("Methodical, Timid, and Quick Thinking", singleSuper.getDescription());
        Assert.assertEquals("One For All", singleSuper.getQuirk());

    }

    @Test
    public void getSupersForSighting() throws SuperPersistenceException { // same for this
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupers = superDao.getAllSupersForSighting(1); // don't know if this is correct
        Assert.assertNotNull(allSupers);

        Super singleSuper = allSupers.get(0);
        Assert.assertEquals("Deku", singleSuper.getSuperName());
        Assert.assertEquals("Methodical, Timid, and Quick Thinking", singleSuper.getDescription());
        Assert.assertEquals("One For All", singleSuper.getQuirk());
    }

    @Test
    public void addSuper() throws SuperPersistenceException {
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupers = superDao.getAllSupers();
        Super toAdd = new Super();
        toAdd.setSuperID(2);
        toAdd.setSuperName("Asta");
        toAdd.setDescription("Yells a lot");
        toAdd.setQuirk("nothing");

        List<Sighting> allSightings = new ArrayList<>();
        Sighting sig = new Sighting();
        sig.setSightingID(1);
        sig.setDate(LocalDate.of(1999, 1, 1));
        allSightings.add(sig);
        toAdd.setAllSightings(allSightings);
        List<Organization> allOrganizations = new ArrayList<>();
        Organization org = new Organization();
        org.setOrganizationID(1);
        org.setOrganizationName("rock");
        allOrganizations.add(org);
        toAdd.setAllOrganizations(allOrganizations);

        Super testSuper = superDao.addSuper(toAdd);

        Assert.assertEquals("Asta", testSuper.getSuperName());
        Assert.assertEquals("Yells a lot", testSuper.getDescription());
        Assert.assertEquals("nothing", testSuper.getQuirk());
    }

    @Test
    public void deleteSuper() throws SuperPersistenceException {
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupersBefore = superDao.getAllSupers();
        Assert.assertEquals(1, allSupersBefore.size());

        superDao.deleteSuper(1);
        List<Super> allSupersAfter = superDao.getAllSupers();
        Assert.assertEquals(0, allSupersAfter.size());
    }

    @Test
    public void editSuper() throws SuperPersistenceException {
        SuperDao superDao = new SuperDaoDB(jdbc);
        List<Super> allSupers = superDao.getAllSupers();
        Super editSuper = allSupers.get(0);

        editSuper.setSuperName("naruto");
        editSuper.setDescription("never give up");
        editSuper.setQuirk("op");

        List<Sighting> allSightings = new ArrayList<>();
        Sighting sig = new Sighting();
        sig.setSightingID(1);
        sig.setDate(LocalDate.of(1999, 1, 1));
        allSightings.add(sig);
        editSuper.setAllSightings(allSightings);
        
        List<Organization> allOrganizations = new ArrayList<>();
        Organization org = new Organization();
        org.setOrganizationID(1);
        org.setOrganizationName("rock");
        allOrganizations.add(org);
        editSuper.setAllOrganizations(allOrganizations);
        
        Super editedSuper = superDao.editSuper(editSuper);
        Assert.assertEquals("naruto", editedSuper.getSuperName());
        Assert.assertEquals("never give up", editedSuper.getDescription());
        Assert.assertEquals("op", editedSuper.getQuirk());
        
        List<Sighting> editedSightings = editedSuper.getAllSightings();
        Sighting editedSighting = editedSightings.get(0);
        Assert.assertEquals(LocalDate.of(1999, 1, 1), editedSighting.getDate());
        
        List<Organization> editedOrgs = editedSuper.getAllOrganizations();
        Organization editedOrg = editedOrgs.get(0);
        Assert.assertEquals("rock", editedOrg.getOrganizationName());
    }
}

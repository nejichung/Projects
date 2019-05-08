/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.daoTest;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.sg.deku.daos.OrganizationDao;
import com.sg.deku.daos.OrganizationDaoDB;
import com.sg.deku.daos.OrganizationPersistenceException;
import com.sg.deku.models.Organization;
import com.sg.deku.models.Super;
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
public class OrganizationDaoTest {

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
    public void getAllOrganizations() throws OrganizationPersistenceException {
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        List<Organization> allOrganizations = orgDao.getAllOrganizations();
        Assert.assertNotNull(allOrganizations);

        Organization toOrg = allOrganizations.get(0);

        Assert.assertEquals("UA", toOrg.getOrganizationName());
        Assert.assertEquals("#1 Academia", toOrg.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", toOrg.getAddress());
        Assert.assertEquals("1234567890", toOrg.getPhoneNumber());
        Assert.assertEquals("allmight@ua.com", toOrg.getEmailAddress());
    }

    @Test
    public void getOrganizationByID() throws OrganizationPersistenceException {
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        Organization ua = orgDao.getOrganizationByID(1);
        Assert.assertNotNull(ua);

        Assert.assertEquals("UA", ua.getOrganizationName());
        Assert.assertEquals("#1 Academia", ua.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", ua.getAddress());
        Assert.assertEquals("1234567890", ua.getPhoneNumber());
        Assert.assertEquals("allmight@ua.com", ua.getEmailAddress());
    }

    @Test
    public void getOrganizationForSuper() throws OrganizationPersistenceException { //look at
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        Super newSup = new Super();
        newSup.setSuperID(1);
        List<Organization> allOrgs = orgDao.getAllOrganizationsForSuper(newSup.getSuperID());
        Assert.assertEquals(1, allOrgs.size());
        Organization org = allOrgs.get(0);

        Assert.assertEquals("UA", org.getOrganizationName());
        Assert.assertEquals("#1 Academia", org.getDescription());
        Assert.assertEquals("123 Yolo Street, Deez 42000", org.getAddress());
        Assert.assertEquals("1234567890", org.getPhoneNumber());
        Assert.assertEquals("allmight@ua.com", org.getEmailAddress());
    }

    @Test
    public void addOrganization() throws OrganizationPersistenceException {
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        List<Organization> allOrganizations = orgDao.getAllOrganizations();

        Organization toAdd = new Organization();
        toAdd.setOrganizationID(2);
        toAdd.setOrganizationName("Akatsuki");
        toAdd.setDescription("Clouds");
        toAdd.setAddress("a big ass cave");
        toAdd.setPhoneNumber("12087539");
        toAdd.setEmailAddress("akatsuki@fucktheworld.com");
         List<Super> allSups = new ArrayList<>();
        Super sup = new Super();
        sup.setSuperName("YOLO");
        sup.setSuperID(1);
        allSups.add(sup);
        toAdd.setAllSupers(allSups);

        Organization testOrg = orgDao.addOrganization(toAdd); // could not access data cuz of super

        Assert.assertEquals("Akatsuki", testOrg.getOrganizationName());
        Assert.assertEquals("Clouds", testOrg.getDescription());
        Assert.assertEquals("a big ass cave", testOrg.getAddress());
        Assert.assertEquals("12087539", testOrg.getPhoneNumber());
        Assert.assertEquals("akatsuki@fucktheworld.com", testOrg.getEmailAddress());
    }

    @Test
    public void editOrg() throws OrganizationPersistenceException {
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        List<Organization> allOrgs = orgDao.getAllOrganizations();
        Organization org = allOrgs.get(0);
        
        org.setOrganizationName("yeet");
        org.setDescription("bleh");
        org.setAddress("1235554 yolo st");
        org.setPhoneNumber("127589");
        org.setEmailAddress("alfjkd@alkjdf.com");
        List<Super> allSups = new ArrayList<>();
        Super sup = new Super();
        sup.setSuperName("YOLO");
        sup.setSuperID(1);
        allSups.add(sup);
        org.setAllSupers(allSups);
        
        Organization editedOrg = orgDao.editOrg(org);
        Assert.assertEquals("yeet", editedOrg.getOrganizationName());
        Assert.assertEquals("bleh", editedOrg.getDescription());
        Assert.assertEquals("1235554 yolo st", editedOrg.getAddress());
        Assert.assertEquals("127589", editedOrg.getPhoneNumber());
        Assert.assertEquals("alfjkd@alkjdf.com", editedOrg.getEmailAddress());
    }

    @Test
    public void deleteOrganization() throws OrganizationPersistenceException {
        OrganizationDao orgDao = new OrganizationDaoDB(jdbc);
        List<Organization> allOrgsBefore = orgDao.getAllOrganizations();
        Assert.assertEquals(1, allOrgsBefore.size());
        
        orgDao.deleteOrganization(1);
        List<Organization> allOrgsAfter = orgDao.getAllOrganizations();
        Assert.assertEquals(0, allOrgsAfter.size()); // was 1???
        
    }

}

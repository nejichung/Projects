///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.resourcehub.daos;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import com.sg.resourcehub.models.Item;
//import com.sg.resourcehub.models.Requester;
//import com.sg.resourcehub.models.Role;
//import com.sg.resourcehub.models.Supplier;
//import com.sg.resourcehub.models.Tag;
//import com.sg.resourcehub.models.User;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// *
// * @author abdulmalik
// */
//public class ResourceHubDaoTest {
//    String testPath = "/Users/abdulmalik/Desktop/mpls-team-super-star/HomelessTest.sql";
//    String seedPath =  "/Users/abdulmalik/Desktop/mpls-team-super-star/HomelessSeed.sql";
//    
//             MysqlDataSource ds = new MysqlDataSource();
//             JdbcTemplate jdbc;
//
//
//    public ResourceHubDaoTest() throws SQLException {
//                ds.setServerName("localhost");
//        ds.setDatabaseName("ResourceHub");
//        ds.setUser("jacob");
//        ds.setPassword("Rootroot123$");
//        ds.setServerTimezone("America/Chicago");
//        ds.setUseSSL(false);
//        ds.setAllowPublicKeyRetrieval(true);
//        
//       jdbc = new JdbcTemplate(ds);
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() throws IOException {
//        //Drop ResourceHub If Exist;
//       jdbc.update("DELECT FROM User");
//       jdbc.update("DELECT FROM Items");
//       jdbc.update("DELECT FROM ItemsTags");
//       jdbc.update("DELECT FROM Requester");
//       jdbc.update("DELECT FROM User_Role");
//       jdbc.update("DELECT FROM Suppliers");
//       jdbc.update("DELECT FROM Tags");
//     //  jdbc.update("INSERT FROM User");
//       
//        throw new UnsupportedOperationException("TODO: reset database here");
// 
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getUserById method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetUserById() throws Exception {  
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        List<User> allUsers = dao.getAllUsers();
//        User newUser = null;
//        for (User toCheck : allUsers) {
//            if(toCheck.getUsername()== "supplier"){
//                newUser = toCheck;
//                Assert.assertEquals(2, newUser.getId());
//            }
//        }
//    }
//
//    /**
//     * Test of getUserByUsername method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetUserByUsername() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        List<User> allUsers = dao.getAllUsers();
//         User newUser = null;
//        for (User toCheck : allUsers) {
//            if(toCheck.getId()== 2){
//                newUser = toCheck;
//                Assert.assertEquals("supplier", newUser.getUsername());
//            }
//        }
//        
//    }
//
//    /**
//     * Test of getAllUsers method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllUsers() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        List<User> allUser = dao.getAllUsers();
//        Assert.assertEquals(2, allUser.size());
//        
//    }
//
//    /**
//     * Test of updateUser method, of class ResourceHubDao.
//     */
//    @Test
//    public void testUpdateUser() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        List<User> allUsers = dao.getAllUsers();
//        User newUser = null;
//        for (User toCheck : allUsers) {
//            if(toCheck.getId()== 2){
//                newUser = toCheck;
//                newUser.setPassword("test123$");
//                dao.updateUser(newUser);
//        List<User> listOfUsers = dao.getAllUsers();
//        User returnEdited = null;
//            for (User toReturn: listOfUsers){
//                if(toReturn.getId()== 2){
//                    returnEdited = toReturn;
//                    Assert.assertEquals("test123$", returnEdited.getPassword());
//                }
//            }
//            }
//        }            
//                    
//    }
//
//    /**
//     * Test of deleteUser method, of class ResourceHubDao.
//     */
//    @Test
//    public void testDeleteUser() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        List<User> allUsers = dao.getAllUsers();
//        User newUser = null;
//        for (User toCheck : allUsers) {
//            if(toCheck.getId()== 3){
//               newUser = toCheck;
//               dao.deleteUser(newUser.getId());
//          
//            }
//        }
//        List<User> users = dao.getAllUsers();
//         Assert.assertEquals(2,users.size());
//    }  
//
//    /**
//     * Test of createUser method, of class ResourceHubDao.
//     */
//    @Test
//    public void testCreateUser() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//            User newUser = new User();
//            newUser.setPassword("root123");
//            Set<Role> newRoleSet = new HashSet<>();
//            Role admin = new Role();
//            admin.setRoleName("ROLE_ADMIN");
//            newRoleSet.add(admin);
//            newUser.setRoles(newRoleSet);
//            newUser.setUsername("Bob");
//            dao.createUser(newUser);
//            List<User> everyUser = dao.getAllUsers();
////            Assert.assertEquals(newUser, everyUser.);
//            
//    }
//
//    /**
//     * Test of getRoleById method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetRoleById() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        
//    }
//
//    /**
//     * Test of getRoleByRole method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetRoleByRole() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getAllRoles method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllRoles() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of deleteRole method, of class ResourceHubDao.
//     */
//    @Test
//    public void testDeleteRole() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of updateRole method, of class ResourceHubDao.
//     */
//    @Test
//    public void testUpdateRole() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of createRole method, of class ResourceHubDao.
//     */
//    @Test
//    public void testCreateRole() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getAllUsersForSupplier method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllUsersForSupplier() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//        
//    }
//
//    /**
//     * Test of getAllUsersForRequester method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllUsersForRequester() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getRequesterForUser method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetRequesterForUser() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getSupplierForUser method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetSupplierForUser() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getRequesters method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetRequesters() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getSuppliers method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetSuppliers() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getItems method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetItems() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getAllTags method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllTags() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of requesterItem method, of class ResourceHubDao.
//     */
//    @Test
//    public void testRequesterItem() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of supplierItem method, of class ResourceHubDao.
//     */
//    @Test
//    public void testSupplierItem() throws Exception {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getItemByID method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetItemByID() {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    /**
//     * Test of getAllTagsForItem method, of class ResourceHubDao.
//     */
//    @Test
//    public void testGetAllTagsForItem() {
//        ResourceHubDBDao dao = new ResourceHubDBDao(jdbc);
//    }
//
//    
//}

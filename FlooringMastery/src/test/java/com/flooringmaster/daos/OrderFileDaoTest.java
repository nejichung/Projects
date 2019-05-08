/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.OrderDao;
import com.softwareguild.flooringmastery.daos.OrderFileDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Software Guld
 */
public class OrderFileDaoTest {

    String seedPath = "SeedOrder";
    String testPath = "TestOrder";

    @BeforeClass
    public static void setupClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() throws IOException {
        File seedFolder = new File(seedPath);
        File testFolder = new File(testPath);
        File[] testFiles = testFolder.listFiles();
        for (File toDelete : testFiles) { // null here
            toDelete.delete();
        }
        File[] seedFiles = seedFolder.listFiles();
        for (File toCopy : seedFiles) {
            Files.copy(toCopy.toPath(), Paths.get(testPath, toCopy.getName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @After
    public void tearDown() {

    }

    @Test
    public void addOrder() throws OrderPersistenceException {
        OrderDao successDao = new OrderFileDao(testPath);
        
        Order toAdd = new Order();
        toAdd.setDate(LocalDate.of(2019, 6, 13));
        toAdd.setName("YASS");
        toAdd.setStateAbbreviation("MI");
        toAdd.setTaxRate(new BigDecimal("6.25"));
        toAdd.setProductType("Tile");
        toAdd.setMatUnitCost(new BigDecimal("5.15"));
        toAdd.setLaborUnitCost(new BigDecimal("4.75"));
        toAdd.setArea(new BigDecimal("13"));
        Order testOrder = successDao.addOrder(toAdd);
        List<Order> allOrders = successDao.getOrdersByDate(LocalDate.of(2019, 6, 13));
        
        Assert.assertEquals(LocalDate.of(2019,6,13), testOrder.getDate());
        Assert.assertEquals("YASS", testOrder.getName());
        Assert.assertEquals("MI", testOrder.getStateAbbreviation());
        Assert.assertEquals(new BigDecimal("6.25"), testOrder.getTaxRate());
        Assert.assertEquals("Tile", testOrder.getProductType());
        Assert.assertEquals(new BigDecimal("5.15"), testOrder.getMatUnitCost());
        Assert.assertEquals(new BigDecimal("4.75"), testOrder.getLaborUnitCost());
        Assert.assertEquals(new BigDecimal("13"), testOrder.getArea());
        //TODO: CALL ADD METHOD ON DAO, NOT THE LIST!!!
        
        //Then, try to retrieve the just added order
        //then validate its fields
        

        
    }

    @Test
    public void getOrdersByDate() throws OrderPersistenceException {
        OrderDao successDao = new OrderFileDao(testPath);
        List<Order> allOrders = successDao.getOrdersByDate(LocalDate.of(2019, 6, 13));
        Assert.assertNotNull(allOrders);
        
        Order testOrder = allOrders.get(0);
        Assert.assertEquals(1, testOrder.getOrderNumber());
        Assert.assertEquals("Rew", testOrder.getName());
        Assert.assertEquals("IN", testOrder.getStateAbbreviation());
//        Assert.assertEquals(6.00, testOrder.getTaxRate());
        Assert.assertEquals("Wood", testOrder.getProductType());
        Assert.assertEquals(new BigDecimal("12")  ,testOrder.getArea()); // ASk david on this one. was 12 expects 12 ????
        List<Order> noOrders = successDao.getOrdersByDate(LocalDate.of(2030, 7, 7));
        Assert.assertEquals(0, noOrders.size());
    }
    @Test
    public void editOrder() throws OrderPersistenceException {
        OrderDao successDao = new OrderFileDao(testPath);
        List<Order> allOrders = successDao.getOrdersByDate(LocalDate.of(2019, 6, 13));
        Order editOrder = allOrders.get(0);
        editOrder.setName("YOLO");
        editOrder.setStateAbbreviation("MI"); //add more information and assert those cuz service could be wack
        Order editedOrder = successDao.editOrder(editOrder);
        Assert.assertEquals(1, editedOrder.getOrderNumber());
        Assert.assertEquals("YOLO", editedOrder.getName());
        Assert.assertEquals("MI", editedOrder.getStateAbbreviation());
        Assert.assertEquals("Wood", editedOrder.getProductType());
        Assert.assertEquals(new BigDecimal("12"), editedOrder.getArea());
        
        //TODO: grab this edited order from the dao
        //      validate its fields
        
    }
    @Test
    public void removeOrder() throws OrderPersistenceException{
          OrderDao successDao = new OrderFileDao(testPath);
       
        successDao.removeOrder(LocalDate.of(2019, 6, 13), 1);
        List<Order> allOrders = successDao.getOrdersByDate(LocalDate.of(2019, 6, 13));
        Assert.assertEquals(1, allOrders.size());
    }

    
}

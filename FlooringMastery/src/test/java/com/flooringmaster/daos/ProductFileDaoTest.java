/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.ProductDao;
import com.softwareguild.flooringmastery.daos.ProductFileDao;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.dtos.Product;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
public class ProductFileDaoTest {

    String seedPath = "Products\\seedProduct.txt";
    String testPath = "Products\\testProduct.txt";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception { // Test our Junit twice and see if the quantity 
        File seedFile = new File(seedPath); // creating new files and giving them names
        File testFile = new File(testPath); // this process of making seed.txt and test.txt will be common for unit testing
        testFile.delete();  // deleting the test file
        Files.copy(seedFile.toPath(), testFile.toPath(), StandardCopyOption.REPLACE_EXISTING);  // copying seed file to new text file
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getProducts() throws ProductPersistenceException{
        ProductDao successDao = new ProductFileDao();
        List<Product> allProducts = successDao.getProducts();
        Assert.assertNotNull(allProducts);
        Assert.assertEquals(4, allProducts.size());
        Product testProduct = allProducts.get(0);
        Assert.assertEquals("Carpet", testProduct.getProductType());
        Assert.assertEquals(new BigDecimal("2.25"), testProduct.getCostPerSquareFoot());
        Assert.assertEquals(new BigDecimal("2.10"), testProduct.getLaborCostPerSquareFoot());
    }
    
    @Test
    public void checkProduct() throws ProductPersistenceException{
        ProductDao successDao = new ProductFileDao();
        List<Product> allProducts = successDao.getProducts();
       
        Product productOne = allProducts.get(0);
        Product productTwo = allProducts.get(1);
        
        Product carpet = successDao.checkProduct(productOne.getProductType());
        Product laminate = successDao.checkProduct(productTwo.getProductType());
        
        Assert.assertEquals("Carpet", carpet.getProductType());
        Assert.assertEquals(new BigDecimal("2.25"), carpet.getCostPerSquareFoot());
        Assert.assertEquals(new BigDecimal("2.10"), carpet.getLaborCostPerSquareFoot());
        
        Assert.assertEquals("Laminate", laminate.getProductType());
        Assert.assertEquals(new BigDecimal("1.75"), laminate.getCostPerSquareFoot());
        Assert.assertEquals(new BigDecimal("2.10"), laminate.getLaborCostPerSquareFoot());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxesDao;
import com.softwareguild.flooringmastery.daos.TaxesFileDao;
import com.softwareguild.flooringmastery.dtos.Tax;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
public class TaxFileDaoTest {
    
      String seedPath = "Taxes\\seedTaxes.txt";
    String testPath = "Taxes\\testTaxes.txt";
    
    
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
    public void getTaxes() throws TaxPersistenceException{
        TaxesDao successDao = new TaxesFileDao();
        List<Tax> allTaxes = successDao.getTaxes();
        
        Assert.assertNotNull(allTaxes);
        Assert.assertEquals(4, allTaxes.size());
        Tax testTax = allTaxes.get(0);
        Assert.assertEquals("OH", testTax.getStateAbbreviation());
        Assert.assertEquals(new BigDecimal("6.25"), testTax.getTaxRate());
    }
    
    @Test
    public void checkState() throws TaxPersistenceException{
        TaxesDao successDao = new TaxesFileDao();
        List<Tax> allTaxes = successDao.getTaxes();
        
        Tax taxOne = allTaxes.get(0);
        Tax taxTwo = allTaxes.get(1);
        Tax ohTax = successDao.checkState(taxOne.getStateAbbreviation());
        Tax paTax = successDao.checkState(taxTwo.getStateAbbreviation());
        
        Assert.assertEquals("OH", ohTax.getStateAbbreviation());
        Assert.assertEquals(new BigDecimal("6.25"), ohTax.getTaxRate());
        
        Assert.assertEquals("PA", paTax.getStateAbbreviation());
        Assert.assertEquals(new BigDecimal("6.75"), paTax.getTaxRate());
    }
}

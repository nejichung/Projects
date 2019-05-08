/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacob
 */
public class ProductFileDao implements ProductDao {

    //tkaing in  aproduct name and output the matching product object
    // be reading from the PRoduct.txt 
    // and looking for a matching material type and then if it is a match we can set the other values such as matUnitCost and LaboryUnitCost
    String path = "Products\\Products.txt";
//    FlooringAuditDaoImpl auditDao;
//    public ProductFileDao(FlooringAuditDaoImpl auditDao) {
//        this.auditDao = auditDao;
//    }
    // HEADER = " product info"

    @Override
    public List<Product> getProducts() throws ProductPersistenceException {
        List<Product> listOfProducts = new ArrayList<>();
        try {

            Scanner reader = new Scanner(new BufferedReader(new FileReader(path)));

            reader.nextLine(); // skips the header row

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] cells = line.split(",");

                Product toBuild = new Product();

                toBuild.setProductType(cells[0]);
                toBuild.setCostPerSquareFoot(new BigDecimal(cells[1])); // parse the string to a big decimal
                toBuild.setLaborCostPerSquareFoot(new BigDecimal(cells[2])); // same as above
                listOfProducts.add(toBuild); // adding the product to our list
            }
            reader.close();

        } catch (FileNotFoundException ex) {
//            auditDao.writeAuditEntry("ProductPersistenceException caught."); 
    }
        return listOfProducts;
    }

    @Override
    public Product checkProduct(String productType) throws ProductPersistenceException { // taking in the string that they type
        Product toReturn = null;
        List<Product> listofProducts = getProducts(); // getting from the method above

        for (Product currentProduct : listofProducts) { // comparing a single product to all the products
            if (productType.equalsIgnoreCase(currentProduct.getProductType())) { // if the product the user inputted is equal to a product in the file
                toReturn = currentProduct;
             }
        }

        return toReturn;
    }

}

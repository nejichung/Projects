/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.ProductDao;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.dtos.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class InMemoryProductDao implements ProductDao{
    List<Product> allProducts = new ArrayList();
    public InMemoryProductDao(){
        
        Product carpet = new Product();
        carpet.setProductType("Carpet");
        carpet.setCostPerSquareFoot(new BigDecimal("2.25"));
        carpet.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        allProducts.add(carpet);
        
        Product laminate = new Product();
        laminate.setProductType("Laminate");
        laminate.setCostPerSquareFoot(new BigDecimal("1.75"));
        laminate.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        allProducts.add(laminate);
        
        Product tile = new Product();
        tile.setProductType("Tile");
        tile.setCostPerSquareFoot(new BigDecimal("3.50"));
        tile.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        allProducts.add(tile);
        
        Product wood = new Product();
        wood.setProductType("Wood");
        wood.setCostPerSquareFoot(new BigDecimal("5.15"));
        wood.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        allProducts.add(wood);
        
    }

    @Override
    public List<Product> getProducts() throws ProductPersistenceException {
        return allProducts;
    }

    @Override
    public Product checkProduct(String productType) throws ProductPersistenceException {
        Product matchingProduct = null;
        
        for (Product toCheck : allProducts){
            if (productType == toCheck.getProductType()){
                matchingProduct = toCheck;
            }
        }
        return matchingProduct;
    }
    
}

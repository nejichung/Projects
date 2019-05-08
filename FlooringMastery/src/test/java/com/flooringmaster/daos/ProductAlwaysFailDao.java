/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.ProductDao;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.dtos.Product;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class ProductAlwaysFailDao implements ProductDao{

    @Override
    public List<Product> getProducts() throws ProductPersistenceException {
        throw new ProductPersistenceException(null);
    }

    @Override
    public Product checkProduct(String productType) throws ProductPersistenceException {
        throw new ProductPersistenceException(null);
    }
    
    
}

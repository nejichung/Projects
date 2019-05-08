/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Product;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface ProductDao {
    public List<Product> getProducts () throws ProductPersistenceException;
        
    public Product checkProduct (String productType) throws ProductPersistenceException;
    
    
}

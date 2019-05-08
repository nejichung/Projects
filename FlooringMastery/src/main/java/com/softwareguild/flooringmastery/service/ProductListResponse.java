/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.softwareguild.flooringmastery.dtos.Product;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class ProductListResponse extends Response {
    
    private List<Product> allProducts;

    /**
     * @return the allProducts
     */
    public List<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * @param allProducts the allProducts to set
     */
    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
    
}

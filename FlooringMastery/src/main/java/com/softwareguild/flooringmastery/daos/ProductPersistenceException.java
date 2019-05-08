/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

/**
 *
 * @author Software Guld
 */
public class ProductPersistenceException extends Exception{
      public ProductPersistenceException(String message){
        super(message);
    }
    public ProductPersistenceException(String message, Throwable ex ){
        super(message, ex);
    }
    
}

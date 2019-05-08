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
public class TaxPersistenceException extends Exception{
      public TaxPersistenceException(String message){
        super(message);
    }
    public TaxPersistenceException(String message, Throwable ex ){
        super(message, ex);
    }
    
}

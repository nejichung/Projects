/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.daos;

/**
 *
 * @author Software Guld
 */
public class PersistenceException extends Exception {
     public PersistenceException (String message){
        super(message);
    }
    public PersistenceException (String message,Throwable cause){
        super(message,cause);
}
    
}

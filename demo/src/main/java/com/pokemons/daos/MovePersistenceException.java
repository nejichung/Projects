/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokemons.daos;

/**
 *
 * @author Jacob
 */
public class MovePersistenceException extends Exception {
      public MovePersistenceException(String message) {
        super(message);
    }

    public MovePersistenceException(String message, Throwable ex) {
        super(message, ex);
    }
    
}  


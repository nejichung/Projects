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
public class PokePersistenceException extends Exception{
    public PokePersistenceException(String message) {
        super(message);
    }

    public PokePersistenceException(String message, Throwable ex) {
        super(message, ex);
    }
    
}

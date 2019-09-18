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
public class NaturePersistenceException extends Exception {
    public NaturePersistenceException(String message) {
        super(message);
    }

    public NaturePersistenceException(String message, Throwable ex) {
        super(message, ex);
    }
}

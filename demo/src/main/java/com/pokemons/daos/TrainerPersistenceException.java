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
public class TrainerPersistenceException extends Exception{
    public TrainerPersistenceException(String message) {
        super(message);
    }

    public TrainerPersistenceException(String message, Throwable ex) {
        super(message, ex);
    }
}

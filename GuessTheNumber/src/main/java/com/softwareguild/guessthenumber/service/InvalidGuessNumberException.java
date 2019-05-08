/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.guessthenumber.service;

/**
 *
 * @author Software Guld
 */
public class InvalidGuessNumberException extends Exception {
    public InvalidGuessNumberException(String message){
        super(message);
    }
    public InvalidGuessNumberException(String message, Throwable ex ){
        super(message, ex);
    }
}

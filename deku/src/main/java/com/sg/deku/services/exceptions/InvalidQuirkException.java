/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.exceptions;

/**
 *
 * @author Jacob
 */
public class InvalidQuirkException extends Exception {
     public InvalidQuirkException(String message) {
        super(message);
    }

    public InvalidQuirkException(String message, Throwable ex) {
        super(message, ex);
    }

}

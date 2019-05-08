/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.deku.services.exceptions;

/**
 *
 * @author Software Guld
 */
public class InvalidPhoneNumberException extends Exception {
       public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException(String message, Throwable ex) {
        super(message, ex);
    }
}

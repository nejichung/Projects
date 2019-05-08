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
public class InvalidAddressException extends Exception {

    public InvalidAddressException(String message) {
        super(message);
    }

    public InvalidAddressException(String message, Throwable ex) {
        super(message, ex);
    }

}

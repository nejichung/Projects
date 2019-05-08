/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.service.exceptions;

/**
 *
 * @author Software Guld
 */
public class InvalidQuantityException extends Exception{
    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException(String message, Throwable ex) {
        super(message, ex);
    }
}

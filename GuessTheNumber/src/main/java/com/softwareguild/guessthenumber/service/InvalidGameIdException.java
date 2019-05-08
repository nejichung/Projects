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
public class InvalidGameIdException extends Exception{
       public InvalidGameIdException(String message){
        super(message);
    }
    public InvalidGameIdException(String message, Throwable ex ){
        super(message, ex);
    }
}

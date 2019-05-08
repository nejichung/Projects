/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.daos;

/**
 *
 * @author abdulmalik
 */
public class DaoPersistenceException extends Exception{
      public DaoPersistenceException(String message){
        super(message);
    }
    public DaoPersistenceException(String message, Throwable innerException){
        super(message, innerException);
    }
}

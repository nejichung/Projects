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
public class GenerateNumberResponse extends Response{
    
    private int GeneratedNumber;

    /**
     * @return the GeneratedNumber
     */
    public int getGeneratedNumber() {
        return GeneratedNumber;
    }

    /**
     * @param GeneratedNumber the GeneratedNumber to set
     */
    public void setGeneratedNumber(int GeneratedNumber) {
        this.GeneratedNumber = GeneratedNumber;
    }
    
}

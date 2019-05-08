/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.dtos;

import java.math.BigDecimal;

/**
 *
 * @author Software Guld
 */
public class Tax {
    private String stateAbbreviation;
    private BigDecimal taxRate;

    /**
     * @return the state
     */
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    /**
     * @param state the state to set
     */
    public void setStateAbbreviation(String state) {
        this.stateAbbreviation = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}

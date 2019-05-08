/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.softwareguild.flooringmastery.dtos.Tax;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class TaxListResponse extends Response {
   private List<Tax> allTaxes;

    /**
     * @return the allTaxes
     */
    public List<Tax> getAllTaxes() {
        return allTaxes;
    }

    /**
     * @param allTaxes the allTaxes to set
     */
    public void setAllTaxes(List<Tax> allTaxes) {
        this.allTaxes = allTaxes;
    }
    
}

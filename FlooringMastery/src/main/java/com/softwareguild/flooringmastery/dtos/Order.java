/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author Software Guld
 */
    public class Order {
    private LocalDate date;
    //OrderNumber,
    private int orderNumber;
    //CustomerName,
    private String name;
    //   StateAbbreviation
    private String stateAbbreviation;
    //TaxRate,
    private BigDecimal taxRate;
    //ProductType,
    private String productType;
    //Area,
    private BigDecimal area;
    //CostPerSquareFoot,
    private BigDecimal matUnitCost;
    //LaborCostPerSquareFoot
    private BigDecimal laborUnitCost;
   

    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }
    
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    
    public BigDecimal getMatUnitCost() {
        return matUnitCost;
    }
    public void setMatUnitCost(BigDecimal matUnitCost) {
        this.matUnitCost = matUnitCost;
    }
    
    public BigDecimal getLaborUnitCost() {
        return laborUnitCost;
    }
    public void setLaborUnitCost(BigDecimal laborUnitCost) {
        this.laborUnitCost = laborUnitCost;
    }
   
    
    
    public BigDecimal getTotalMatCost(){
        BigDecimal toReturn = area.multiply(matUnitCost).setScale(2, RoundingMode.HALF_UP);
        return toReturn;
    }
    public BigDecimal getTotalLaborCost(){
        BigDecimal toReturn = area.multiply(laborUnitCost).setScale(2, RoundingMode.HALF_UP);
        return toReturn;
    }
    public BigDecimal getTotalTax(){
        BigDecimal totalMatCost = getTotalMatCost();
        BigDecimal totalLaborCost = getTotalLaborCost();
        
        BigDecimal preTax = totalMatCost.add(totalLaborCost);
        
        BigDecimal toReturn = preTax.multiply(taxRate).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        
        return toReturn;
    }
    public BigDecimal getOrderTotal(){
        BigDecimal totalMatCost = getTotalMatCost();
        BigDecimal totalLaborCost = getTotalLaborCost();
        BigDecimal totalTaxes = getTotalTax();
        
        BigDecimal toReturn = totalMatCost
                             .add(totalLaborCost)
                             .add(totalTaxes).setScale(2, RoundingMode.HALF_UP);
        
        return toReturn;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}




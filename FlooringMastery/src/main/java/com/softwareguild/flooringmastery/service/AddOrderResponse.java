/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.softwareguild.flooringmastery.dtos.Order;

/**
 *
 * @author Software Guld
 */
public class AddOrderResponse extends Response {
    
    private Order order;

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    
    
}

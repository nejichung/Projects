/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.softwareguild.flooringmastery.dtos.Order;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class ListOrderResponse extends Response {
    private List<Order> allOrders;

    /**
     * @return the allOrders
     */
    public List<Order> getAllOrders() {
        return allOrders;
    }

    /**
     * @param allOrders the allOrders to set
     */
    public void setAllOrders(List<Order> allOrders) {
        this.allOrders = allOrders;
    }
    
}

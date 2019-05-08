/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public interface OrderDao {

    public Order addOrder(Order toAdd) throws OrderPersistenceException; // eventually 

    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException;

    public Order editOrder(Order toEdit) throws OrderPersistenceException;

    public void removeOrder(LocalDate date, int OrderNumber) throws OrderPersistenceException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.OrderDao;
import com.softwareguild.flooringmastery.daos.OrderFileDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class OrderAlwaysFailDao implements OrderDao  {

    @Override
    public Order addOrder(Order toAdd) throws OrderPersistenceException {
        throw new OrderPersistenceException(null);
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException {
        throw new OrderPersistenceException(null);
        }

    @Override
    public Order editOrder(Order toEdit) throws OrderPersistenceException {
      throw new OrderPersistenceException(null); 
    }
    @Override
    public void removeOrder(LocalDate date, int OrderNumber) throws OrderPersistenceException {
        throw new OrderPersistenceException(null);
    }
}

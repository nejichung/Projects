/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmaster.daos;

import com.softwareguild.flooringmastery.daos.OrderDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Software Guld
 */
public class InMemoryOrderDao implements OrderDao {

    Map<LocalDate, List<Order>> allOrders = new HashMap<LocalDate, List<Order>>(); // dafuq

    public InMemoryOrderDao() {
        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));
        first.setTaxRate(new BigDecimal("5"));
        first.setLaborUnitCost(new BigDecimal("10"));
        first.setMatUnitCost(new BigDecimal("15"));
        allOrders.put(date, new ArrayList<Order>());
        allOrders.get(date).add(first);
        
        Order v2 = new Order();
        v2.setDate(date);
        v2.setOrderNumber(2);
        v2.setName("alfj");
        v2.setStateAbbreviation("In");
        v2.setProductType("wood");
        v2.setArea(new BigDecimal("23"));
        allOrders.get(date).add(v2);
        

        Order second = new Order();
        LocalDate date2 = LocalDate.of(2060, 7, 9);
        second.setDate(date2);
        second.setOrderNumber(1);
        second.setName("Maou");
        second.setStateAbbreviation("MN");
        second.setProductType("Laminate"); 
        second.setArea(new BigDecimal("10"));
        allOrders.put(date2, new ArrayList<Order>());
        allOrders.get(date2).add(second);

    }

    @Override
    public Order addOrder(Order toAdd) {

        Order toReturn = toAdd;

        if (!allOrders.containsKey(toAdd.getDate())) {
            allOrders.put(toAdd.getDate(), new ArrayList<Order>());
        }

        allOrders.get(toAdd.getDate()).add(toAdd);

        return toReturn;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException {
        // make a new list and replace 
        List<Order> newAllOrders = new ArrayList();
        
        if(allOrders.containsKey(lookup)){
            newAllOrders = allOrders.get(lookup);
        }
        
        return newAllOrders;
    }

    @Override
    public Order editOrder(Order toEdit) {
        int index = Integer.MIN_VALUE;
        List<Order> allOrdersForDateToEdit = allOrders.get(toEdit.getDate());
        int id = toEdit.getOrderNumber();
        for (int i = 0; i < allOrdersForDateToEdit.size(); i++){
           Order toCheck = allOrdersForDateToEdit.get(i);
           if(toCheck.getOrderNumber() == id){
               index = i;
//               break;
           }
                if(index >=0 && index < allOrdersForDateToEdit.size()){ // it skips this line
            allOrdersForDateToEdit.set(index, toEdit);
                break;
            
    }
           }return toEdit;
        }
    @Override
    public void removeOrder(LocalDate date, int OrderNumber) {
        int index = Integer.MIN_VALUE;
        List<Order> allOrdersForDate = allOrders.get(date);
                for (int i = 0; i <allOrdersForDate.size(); i++) {
            Order toCheck = allOrdersForDate.get(i);
                if (toCheck.getOrderNumber() == OrderNumber){
                    index = i;
                    
                    allOrdersForDate.remove(index);
                    break;
    }

}
    }
}
       

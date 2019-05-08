/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.daos;

import com.softwareguild.flooringmastery.dtos.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software Guld
 */
public class OrderFileDao implements OrderDao { // we will need daos for every object

    List<Order> allOrders = new ArrayList<Order>(); // creating a new arrayLIst
    String directory = "Orders"; // contructor to bring the directory in

    public OrderFileDao(String directory) {
        this.directory = directory;
        
    }

    @Override
    public Order addOrder(Order toAdd) throws OrderPersistenceException {

        Order toReturn = toAdd;
        List<Order> allAddOrders = new ArrayList<Order>();
         
            allAddOrders = getOrdersByDate(toAdd.getDate());
            allAddOrders.add(toAdd);


//        int newOrderNumber = genNewOrderNumber(allOrders);
//        toAdd.setOrderNumber(newOrderNumber);
        allOrders = allAddOrders;
        boolean success = writeFile(toAdd.getDate());

        if (!success) {
            toReturn = null;
        }

        return toReturn;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate lookup) throws OrderPersistenceException {
        List<Order> allOrdersByDate = new ArrayList<Order>();
        try {
            String path = dateToPath(lookup); // getting our path from the dateToPath

            Scanner reader = new Scanner(new BufferedReader(new FileReader(dateToPath(lookup)))); // read the filein the path
            reader.nextLine();
            while (reader.hasNextLine()) { // this will keep going as long as there is data on the next line
                String line = reader.nextLine(); // we read all the info in 1 row

                String[] cells = line.split(","); // splitting all our cells to ignore the comma

                Order toBuild = new Order(); // new order object

                toBuild.setOrderNumber(Integer.parseInt(cells[0])); // reading all the info in the array list
                toBuild.setName(cells[1]);
                toBuild.setStateAbbreviation(cells[2]);
                toBuild.setTaxRate(new BigDecimal(cells[3]));
                toBuild.setProductType(cells[4]);
                toBuild.setArea(new BigDecimal(cells[5]));
                toBuild.setMatUnitCost(new BigDecimal(cells[6]));
                toBuild.setLaborUnitCost(new BigDecimal(cells[7]));
                toBuild.setDate(lookup); // setting the date to whatever they inputted
                // don't need the other remaining info since they are calculated in the Orders dto
                allOrdersByDate.add(toBuild); // adding the order to the list
            }
            reader.close();
        } catch (FileNotFoundException ex) {
//            throw new OrderPersistenceException("Could not load data into memory.");
            // if the file can't be found
            // no ordersfor this date
            // that'sfine just leave the list empty
        }
//        allOrders = allOrdersByDate;
        return allOrdersByDate;
    }

    @Override
    public Order editOrder(Order toEdit) throws OrderPersistenceException {
        Order toReturn = null;
        List<Order> allEditOrders = new ArrayList<>();
        int index = Integer.MIN_VALUE;
        
        int id = toEdit.getOrderNumber();
        try {
            allEditOrders = getOrdersByDate(toEdit.getDate());
       
            
        for (int i = 0; i < allEditOrders.size(); i++){
           Order toCheck = allEditOrders.get(i);
           if(toCheck.getOrderNumber() == id){
               index = i;
               break;
           }
        }
        if(index >=0 && index < allEditOrders.size()){
            allEditOrders.set(index, toEdit);
            
            allOrders = allEditOrders;
            boolean success = writeFile(toEdit.getDate());
            if (success){
                toReturn = toEdit;
            }
            
             } 
                     }
        catch (OrderPersistenceException ex) {
//              auditDao.writeAuditEntry("OrderPersistenceException caught.");return toReturn;
        
        } return toReturn;
    }

    @Override
    public void removeOrder(LocalDate date, int OrderNumber) throws OrderPersistenceException {
        List<Order> allRemoveOrders = new ArrayList<>();
        int index = Integer.MIN_VALUE;
        try {
            allRemoveOrders = getOrdersByDate(date); // get all the orders by date from the method
        
//            auditDao.writeAuditEntry("OrderPersistenceException caught.");
        
        for (int i = 0; i <allRemoveOrders.size(); i++) {
            Order toCheck = allRemoveOrders.get(i);
                if (toCheck.getOrderNumber() == OrderNumber){
                    index = i;
                    
                    allRemoveOrders.remove(index);
                    break;
                }
            
//
//            if (matchingOrder.getOrderNumber() != OrderNumber) { //if it doesn't match we want to add ot new list
//                newOrdersForDate.add(matchingOrder); // adding the matching order to the new Order list

            } 
            allOrders = allRemoveOrders;
            boolean success = writeFile(date);
            } catch (OrderPersistenceException ex) {
            }
    }

    private String dateToPath(LocalDate date) { // taking in a date and then making a file name out of it
        String convertedDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy")); // this will make the date have this pattern
        String fileName = "Orders_" + convertedDate + ".txt"; // this will name our file
        String path = directory + "\\" + fileName; // this will name our folder and name

        return path;

    }

    private boolean writeFile(LocalDate toAdd) throws OrderPersistenceException { // there are multiple items so fuck me
        boolean success = false;
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(dateToPath(toAdd))); // the date that needs to be put is the date that the user entered when creating a date
            pw.println();
            for (Order toWrite : allOrders) {
                String line = orderToLine(toWrite);
                pw.println(line);
            }
            pw.flush();
            pw.close();

            success = true;
        } catch (IOException ex) {
            throw new OrderPersistenceException("Error writing to file: " + dateToPath(toAdd), ex);
        }
        return success;
    }

    private String orderToLine(Order toWrite) {

        //Order#,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total 
//        if(!file.exists){
        String toReturn
                = toWrite.getOrderNumber() + ","
                + toWrite.getName() + ","
                + toWrite.getStateAbbreviation() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getMatUnitCost() + ","
                + toWrite.getLaborUnitCost() + ","
                + toWrite.getTotalMatCost() + ","
                + toWrite.getTotalLaborCost() + ","
                + toWrite.getTotalTax() + ","
                + toWrite.getOrderTotal();
        return toReturn;
    }

//    public int genNewOrderNumber(List<Order> allOrders) {
//        int genOrderNumber = 0;
////        List <Order> allOrders = new ArrayList<>();
////        try {
////            allOrders = getOrdersByDate(toAdd.getDate());
////        } catch (OrderPersistenceException ex) {
////            Logger.getLogger(OrderFileDao.class.getName()).log(Level.SEVERE, null, ex);
////        } genOrderNumber = allOrders.size() + 1;
////        return genOrderNumber;
//        if (allOrders.isEmpty()) {
//            genOrderNumber = 1;
//        } else {
//            for (Order toCheck : allOrders) {
//                if (toCheck.getOrderNumber() > genOrderNumber) {
//                    genOrderNumber = toCheck.getOrderNumber();
//                }
//            }
//            genOrderNumber++;
//        }
//        return genOrderNumber;
//    }


}

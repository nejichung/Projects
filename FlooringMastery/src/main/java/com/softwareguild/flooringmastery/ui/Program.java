/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import com.softwareguild.flooringmastery.daos.FlooringAuditDaoImpl;
import com.softwareguild.flooringmastery.daos.OrderFileDao;
import com.softwareguild.flooringmastery.service.FloorService;
import com.softwareguild.flooringmastery.daos.OrderDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.daos.ProductDao;
import com.softwareguild.flooringmastery.daos.ProductFileDao;
import com.softwareguild.flooringmastery.daos.ProductPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxPersistenceException;
import com.softwareguild.flooringmastery.daos.TaxesDao;
import com.softwareguild.flooringmastery.daos.TaxesFileDao;
import com.softwareguild.flooringmastery.dtos.Order;
import com.softwareguild.flooringmastery.service.InvalidAreaException;
import com.softwareguild.flooringmastery.service.InvalidDateException;
import com.softwareguild.flooringmastery.service.InvalidNameException;
import com.softwareguild.flooringmastery.service.InvalidProductException;
import com.softwareguild.flooringmastery.service.InvalidStateException;
import static com.softwareguild.flooringmastery.ui.MainMenu.ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Software Guld
 */
public class Program {
    // WE NEED TO PUT read date in our USER IO and CONSOLE IO
    public static void main(String[] args) throws ProductPersistenceException, TaxPersistenceException, InvalidAreaException, InvalidDateException, InvalidNameException, InvalidProductException, InvalidStateException, OrderPersistenceException {
        // creating an ojbect that the file dao will reference
        FlooringAuditDaoImpl auditDao = new FlooringAuditDaoImpl();
        OrderDao orderDao = new OrderFileDao("Orders");
        ProductDao productDao = new ProductFileDao();
        TaxesDao taxesDao = new TaxesFileDao();
                
        
//       String userInput = "06/01/2013"; // getting user input for date
//       LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));  // now we are ableto make the user input into a localdate
       
//        List<Order> ordersByDate = dao.getOrdersByDate(date); // getting a order by date putting in the date
        FloorService service = new FloorService(orderDao, productDao, taxesDao);
        MainMenu.run(service);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.service;

import com.flooringmaster.daos.InMemoryOrderDao;
import com.flooringmaster.daos.InMemoryProductDao;
import com.flooringmaster.daos.InMemoryTaxDao;
import com.flooringmaster.daos.OrderAlwaysFailDao;
import com.flooringmaster.daos.ProductAlwaysFailDao;
import com.flooringmaster.daos.TaxAlwaysFailDao;
import com.softwareguild.flooringmastery.daos.OrderPersistenceException;
import com.softwareguild.flooringmastery.dtos.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jacob
 */
public class FloorServiceTest {

    public FloorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of AddOrder method, of class FloorService.
     */
    @Test
    public void testAddOrderSuccess() throws OrderPersistenceException {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        LocalDate date = LocalDate.of(2020, 9, 13);
//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(date, "Rew", "Wood", "IN", new BigDecimal("12"));
        assertTrue(response.getSuccess());
        int newOrderNumber = response.getOrder().getOrderNumber();
        List<Order> allOrders = successOrderDao.getOrdersByDate(date);
        Order matchedOrder = null;
        for (Order toCheck : allOrders) {
            if (toCheck.getOrderNumber() == newOrderNumber) {
                matchedOrder = toCheck;

            }
        }
        Assert.assertEquals("Rew", matchedOrder.getName());
        Assert.assertEquals("Wood", matchedOrder.getProductType());
        Assert.assertEquals(new BigDecimal("4.75"), matchedOrder.getLaborUnitCost());
        Assert.assertEquals(new BigDecimal("5.15"), matchedOrder.getMatUnitCost());
        Assert.assertEquals("IN", matchedOrder.getStateAbbreviation());
        Assert.assertEquals(new BigDecimal("6"), matchedOrder.getTaxRate());
        Assert.assertEquals(new BigDecimal("12"), matchedOrder.getArea());

        //TODO: get list of orders from the order dao for the same day
        //loop through looking for the order that matches newOrderNumber
        //assert that that has all the right data
    }

    @Test
    public void testAddOrderOrderDaoFail() {
        OrderAlwaysFailDao orderFailDao = new OrderAlwaysFailDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(orderFailDao, successProductDao, successTaxDao);
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Wood", "IN", new BigDecimal("12"));

        assertFalse(response.getSuccess());

    }

    @Test
    public void testAddOrderProductDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        ProductAlwaysFailDao productFailDao = new ProductAlwaysFailDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(successOrderDao, productFailDao, successTaxDao);
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Wood", "IN", new BigDecimal("12"));

        assertFalse(response.getSuccess());
    }

    @Test
    public void testAddOrderTaxesDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        TaxAlwaysFailDao taxFailDao = new TaxAlwaysFailDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, taxFailDao);
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Wood", "IN", new BigDecimal("12"));

        assertFalse(response.getSuccess());

    }

    @Test
    public void testAddOrderFailInvalidDate() throws OrderPersistenceException {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        LocalDate date = LocalDate.of(1020, 9, 13);
//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(date, "Rew", "Wood", "IN", new BigDecimal("12"));
        assertNull(response.getOrder());
        assertFalse(response.getSuccess());
;

    }

    @Test
    public void testAddOrderFailInvalidState() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Wood", "MN", new BigDecimal("12"));
        assertFalse(response.getSuccess());
        assertNull(response.getOrder());
    }

    @Test
    public void testAddOrderFailInvalidArea() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Wood", "IN", new BigDecimal("0"));
        assertFalse(response.getSuccess());

    }

    @Test
    public void testAddOrderFailInvalidName() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "", "Wood", "IN", new BigDecimal("12"));
        assertFalse(response.getSuccess());
    }

    @Test
    public void testAddOrderFailInvalidProduct() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

//        Order newOrder = new Order();
        AddOrderResponse response = service.AddOrder(LocalDate.of(2019, 6, 13), "Rew", "Woohoo", "IN", new BigDecimal("12"));
        assertFalse(response.getSuccess());

    }

    /**
     * Test of DisplayOrder method, of class FloorService.
     */
    @Test
    public void testDisplayOrderSuccess() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        ListOrderResponse response = service.DisplayOrder(LocalDate.of(2019, 6, 13)); // EVENTUALLY REPLACE WITH THE DATE FROM FILE INT HE FILEDAOTEST
        assertTrue(response.getSuccess());
        List<Order> allOrders = response.getAllOrders();
//        successOrderDao.getOrdersByDate(LocalDate.of(2019, 6, 13));
        Order checkerino = allOrders.get(allOrders.size() - 1);

        assertEquals("alfj", checkerino.getName());
        assertEquals("In", checkerino.getStateAbbreviation());
//        assertEquals(new BigDecimal("6"), checkerino.getTaxRate()); null
        assertEquals("wood", checkerino.getProductType());
//        assertEquals(new BigDecimal("5.15"), checkerino.getLaborUnitCost()); null
//        assertEquals(new BigDecimal("4.75"), checkerino.getMatUnitCost()); null
        assertEquals(new BigDecimal("23"), checkerino.getArea());
        //TODO: pull the last order off the list and assert that its fields are what
        //      you expect
    }

    @Test
    public void testDisplayOrderOrderDaoFail() {
        OrderAlwaysFailDao failOrderDao = new OrderAlwaysFailDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(failOrderDao, successProductDao, successTaxDao);
        ListOrderResponse response = service.DisplayOrder(LocalDate.of(2019, 6, 13)); // EVENTUALLY REPLACE WITH THE DATE FROM FILE INT HE FILEDAOTEST
        assertFalse(response.getSuccess());
    }

    /**
     * Test of RemoveOrder method, of class FloorService.
     */
    @Test
    public void testRemoveOrderSuccess() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        ListOrderResponse listResponseBefore = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        RemoveOrderResponse response = service.RemoveOrder(1, LocalDate.of(2019, 6, 13));

        ListOrderResponse listResponseAfter = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        Assert.assertEquals(listResponseBefore.getAllOrders().size(), listResponseAfter.getAllOrders().size());

    }

    @Test
    public void testRemoveOrderWrongDate() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        ListOrderResponse listResponseBefore = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        RemoveOrderResponse response = service.RemoveOrder(1, LocalDate.of(2077, 6, 13));
        ListOrderResponse listResponseAfter = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void testRemoveOrderWrongOrderNumber() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        ListOrderResponse listResponseBefore = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        RemoveOrderResponse response = service.RemoveOrder(10, LocalDate.of(2019, 6, 13));
        ListOrderResponse listResponseAfter = service.DisplayOrder(LocalDate.of(2019, 6, 13));
        Assert.assertFalse(response.getSuccess());
    }

    @Test
    public void testRemoveOrderOrderDaoFail() {
        OrderAlwaysFailDao failOrderDao = new OrderAlwaysFailDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(failOrderDao, successProductDao, successTaxDao);
        RemoveOrderResponse response = service.RemoveOrder(1, LocalDate.of(2019, 6, 13));
        assertFalse(response.getSuccess());
    }

    /**
     * Test of editOrder method, of class FloorService.
     */
    @Test
    public void testEditOrderSuccess() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

        ListOrderResponse validationResponse = service.DisplayOrder(LocalDate.of(2019, 6, 13)); // here date is not the edited Order
        List<Order> allOrdersForDate = validationResponse.getAllOrders();

        Order validationOrder = null;
        for (Order toCheck : allOrdersForDate) {
            if (toCheck.getOrderNumber() == 1) {
                validationOrder = toCheck;
                break;
            }
        }
        validationOrder.setName("Wooper");
        validationOrder.setStateAbbreviation("MI");
        //validationOrder.setTaxRate(new BigDecimal("5.75"));
        validationOrder.setProductType("Tile");
        //validationOrder.setMatUnitCost(new BigDecimal("3.50"));
        //validationOrder.setLaborUnitCost(new BigDecimal("4.15"));
        validationOrder.setArea(new BigDecimal("100"));

        EditOrderResponse response = service.editOrder(validationOrder);
        assertTrue(response.getSuccess());

        Order editedOrder = successOrderDao.editOrder(validationOrder);
        assertEquals("Wooper", editedOrder.getName());
        assertEquals("MI", editedOrder.getStateAbbreviation());
        assertEquals(new BigDecimal("5.75"), editedOrder.getTaxRate());
        assertEquals("Tile", editedOrder.getProductType());
        assertEquals(new BigDecimal("3.50"), editedOrder.getMatUnitCost());
        assertEquals(new BigDecimal("4.15"), editedOrder.getLaborUnitCost());

        assertEquals(new BigDecimal("100"), editedOrder.getArea());

    }

    @Test
    public void testEditOrderOrderDaoFail() {
        OrderAlwaysFailDao orderFailDao = new OrderAlwaysFailDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(orderFailDao, successProductDao, successTaxDao);

        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderProductDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        ProductAlwaysFailDao productFailDao = new ProductAlwaysFailDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();
        FloorService service = new FloorService(successOrderDao, productFailDao, successTaxDao);

        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderTaxesDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        TaxAlwaysFailDao taxFailDao = new TaxAlwaysFailDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, taxFailDao);

        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderFailInvalidState() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        // ???
        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("BLEH"); // invalid STate
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderFailInvalidArea() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        // ???
        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("-1")); // invalid area

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderFailInvalidName() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        // ???
        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName(""); // invalid Name
        first.setStateAbbreviation("IN");
        first.setProductType("Wood");
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());

    }

    @Test
    public void testEditOrderFailInvalidProduct() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);
        // ???
        Order first = new Order();
        LocalDate date = LocalDate.of(2019, 6, 13);
        first.setDate(date);
        first.setOrderNumber(1);
        first.setName("Rew");
        first.setStateAbbreviation("IN");
        first.setProductType("Nuts"); // invalid ProductName
        first.setArea(new BigDecimal("12"));

        EditOrderResponse response = service.editOrder(first);

        assertFalse(response.getSuccess());
        assertNull(response.getEditedOrder());

    }

    /**
     * Test of getAllProducts method, of class FloorService.
     */
    @Test
    public void testGetAllProductsSuccess() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

        ProductListResponse response = service.getAllProducts();

        assertTrue(response.getSuccess());
        assertEquals(4, response.getAllProducts().size());
        //TODO: assert the size of the list that comes back

    }

    @Test
    public void testGetAllProductsProductDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        ProductAlwaysFailDao failProductDao = new ProductAlwaysFailDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, failProductDao, successTaxDao);

        ProductListResponse response = service.getAllProducts();

        assertFalse(response.getSuccess());
    }

    /**
     * Test of getAllTaxes method, of class FloorService.
     */
    @Test
    public void testGetAllTaxesSuccess() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        InMemoryTaxDao successTaxDao = new InMemoryTaxDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, successTaxDao);

        TaxListResponse response = service.getAllTaxes();

        assertTrue(response.getSuccess());
        assertEquals(3, response.getAllTaxes().size());
        //TODO: same as the get all products check (list length and maybe data validation)
    }

    @Test
    public void testGetAllTaxesTaxesDaoFail() {
        InMemoryOrderDao successOrderDao = new InMemoryOrderDao();
        InMemoryProductDao successProductDao = new InMemoryProductDao();
        TaxAlwaysFailDao failTaxDao = new TaxAlwaysFailDao();

        FloorService service = new FloorService(successOrderDao, successProductDao, failTaxDao);

        TaxListResponse response = service.getAllTaxes();

        assertFalse(response.getSuccess());
    }

}

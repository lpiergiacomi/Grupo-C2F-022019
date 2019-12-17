package unq.tpi.desapp;

import org.junit.Test;
import unq.tpi.desapp.exceptions.InsufficientCreditException;
import unq.tpi.desapp.exceptions.InvalidDeliveryDateException;
import unq.tpi.desapp.exceptions.MenuSalesExceededException;
import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.builders.ClientBuilder;
import unq.tpi.desapp.builders.MenuBuilder;
import unq.tpi.desapp.builders.MenuOrderBuilder;
import unq.tpi.desapp.builders.ProviderBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class ClientTest {

    /*
        Start Test builder
     */

    @Test
    public void firstNameTest() {
        Client esteban = new ClientBuilder().withFirstName("Esteban").build();
        assertEquals(esteban.getFirstName(), "Esteban");
    }

    @Test
    public void lastNameTest() {
        Client esteban = new ClientBuilder().withLastName("Matas").build();
        assertEquals(esteban.getLastName(), "Matas");
    }

    @Test
    public void mailTest() {
        Client esteban = new ClientBuilder().withMail("estebanmatas13@gmail.com").build();
        assertEquals(esteban.getMail(), "estebanmatas13@gmail.com");
    }

    @Test
    public void phoneTest() {
        Client esteban = new ClientBuilder().withPhone("1122334455").build();
        assertEquals(esteban.getPhone(), "1122334455");
    }

    @Test
    public void localityTest() {
        Client esteban = new ClientBuilder().withLocality("Quilmes").build();
        assertEquals(esteban.getLocality(), "Quilmes");
    }

    @Test
    public void addressTest() {
        Client esteban = new ClientBuilder().withAddress("Av Calchaqui 666").build();
        assertEquals(esteban.getAddress(), "Av Calchaqui 666");
    }

    @Test
    public void creditTest() {
        Client esteban = new ClientBuilder().withCredit(150).build();
        assertEquals(esteban.getCredit(), 150);
    }

    /*
        End Test builder
     */

    @Test
    public void increaseCreditTest() {
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.increaseCredit(200);
        assertEquals(esteban.getCredit(), 350);
    }

    @Test
    public void withdrawCreditTest() {
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.discountCredit(50);
        assertEquals(esteban.getCredit(), 100);
    }

    @Test
    public void hasNoCreditsForMenuTest() {
        Client esteban = new ClientBuilder().withCredit(80).build();
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(1).build();
        assertFalse(esteban.hasEnoughCredit(menuOrder));
    }


    @Test(expected = InvalidDeliveryDateException.class)
    public void createPaymentOrderWithLessThan48HoursTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(1);
        Client esteban = new ClientBuilder().build();
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withDeliveryDate(deliveryDate).build();

        esteban.paymentOrder(menuOrder);
    }

    @Test(expected = MenuSalesExceededException.class)
    public void createOrderWithoutEnoughMenusTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(9).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withDeliveryDate(deliveryDate).withQuantity(10).build();


        esteban.paymentOrder(menuOrder);
    }

    @Test(expected = InsufficientCreditException.class)
    public void createOrderWithoutEnoughCreditsTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(90).build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withDeliveryDate(deliveryDate).withQuantity(1).build();
        esteban.paymentOrder(menuOrder);
    }

    @Test
    public void createValidOrderTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(1000).withMail("asasa@asasa.com").build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withDeliveryDate(deliveryDate).build();
        assertEquals(esteban.getOrders().size(), 0);
        esteban.paymentOrder(menuOrder);
        assertEquals(esteban.getOrders().size(), 1);
    }

}





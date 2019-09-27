package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.exceptions.InsufficientCreditException;
import unq.tpi.desapp.exceptions.InvalidDeliveryDateException;
import unq.tpi.desapp.exceptions.MenuSalesExceededException;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.model.menu.MenuOrder;
import unq.tpi.desapp.model.builders.ClientBuilder;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.MenuOrderBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

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
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);
        assertFalse(esteban.hasEnoughCredit(menuOrders));
    }


    @Test(expected = InvalidDeliveryDateException.class)
    public void createPaymentOrderWithLessThan48HoursTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(1);
        Client esteban = new ClientBuilder().build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).build();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);

        esteban.paymentOrder(provider, menuOrders, deliveryDate);
    }

    @Test(expected = MenuSalesExceededException.class)
    public void createOrderWithoutEnoughMenusTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(9).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(10).build();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);

        esteban.paymentOrder(provider, menuOrders, deliveryDate);
    }

    @Test(expected = InsufficientCreditException.class)
    public void createOrderWithoutEnoughCreditsTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(90).build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(1).build();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);
        esteban.paymentOrder(provider, menuOrders, deliveryDate);
    }

    @Test
    public void createValidOrderTest() {
        LocalDateTime deliveryDate = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(1000).withMail("asasa@asasa.com").build();
        Provider provider = new ProviderBuilder().withMail("asasa@asasa.com").build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).build();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);
        assertEquals(esteban.getOrders().size(), 0);
        esteban.paymentOrder(provider, menuOrders, deliveryDate);
        assertEquals(esteban.getOrders().size(), 1);
    }

}





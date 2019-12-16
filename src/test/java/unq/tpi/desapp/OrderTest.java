package unq.tpi.desapp;

import org.junit.Test;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.model.Order;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.builders.ClientBuilder;

import unq.tpi.desapp.builders.MenuOrderBuilder;
import unq.tpi.desapp.builders.OrderBuilder;
import unq.tpi.desapp.builders.ProviderBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    /*
        Start Test builder
     */

    @Test
    public void providerTest() {
        Provider provider = new ProviderBuilder().withName("McDonalds").build();
        Order order = new OrderBuilder().withProvider(provider).build();
        assertEquals(order.getProvider(), provider);
    }

    @Test
    public void clientTest() {
        Client client = new ClientBuilder().withFirstName("Esteban").build();
        Order order = new OrderBuilder().withClient(client).build();
        assertEquals(order.getClient(), client);
    }

    @Test
    public void menuOrdersTest() {
        List<MenuOrder> menuOrders = new ArrayList<>();
        MenuOrder menuOrder = new MenuOrderBuilder().withQuantity(1).build();
        menuOrders.add(menuOrder);
        Order order = new OrderBuilder().withMenuOrders(menuOrders).build();
        assertEquals(order.getMenuOrders(), menuOrders);
    }

    @Test
    public void deliveryDeliveryTypeTest() {
        Order order = new OrderBuilder().withDeliveryType("tipoDelivery").build();
        assertEquals(order.getDeliveryType(), "tipoDelivery");
    }


    @Test
    public void deliveryDateTest() {
        LocalDateTime date = LocalDateTime.of(2019, 07, 22, 9, 30);
        Order order = new OrderBuilder().withDeliveryDate(date).build();
        assertEquals(order.getDeliveryDate(), date);
    }

    @Test
    public void deliveryHourTest() {
        LocalTime time = LocalTime.of(9, 30);
        Order order = new OrderBuilder().withDeliveryHour(time).build();
        assertEquals(order.getDeliveryHour(), time);
    }

    @Test
    public void amountTest() {
        Order order = new OrderBuilder().withAmount(200).build();
        assertEquals(order.getAmount(), 200);
    }


    /*
        End Test builder
     */


}




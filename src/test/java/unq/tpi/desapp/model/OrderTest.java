package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.builders.ClientBuilder;

import unq.tpi.desapp.model.builders.MenuOrderBuilder;
import unq.tpi.desapp.model.builders.OrderBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

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
    public void testProvider() {
        Provider provider = new ProviderBuilder().withName("McDonalds").build();
        Order order = new OrderBuilder().withProvider(provider).build();
        assertEquals(order.getProvider(), provider);
    }

    @Test
    public void testClient() {
        Client client = new ClientBuilder().withFirstName("Esteban").build();
        Order order = new OrderBuilder().withClient(client).build();
        assertEquals(order.getClient(), client);
    }

    @Test
    public void testMenuOrders() {
        List<MenuOrder> menuOrders = new ArrayList<>();
        MenuOrder menuOrder = new MenuOrderBuilder().withQuantity(1).build();
        menuOrders.add(menuOrder);
        Order order = new OrderBuilder().withMenuOrders(menuOrders).build();
        assertEquals(order.getMenuOrders(), menuOrders);
    }

    @Test
    public void testDeliveryDeliveryType() {
        Order order = new OrderBuilder().withDeliveryType("tipoDelivery").build();
        assertEquals(order.getDeliveryType(), "tipoDelivery");
    }


    @Test
    public void testDeliveryDate() {
        LocalDateTime date = LocalDateTime.of(2019, 07, 22, 9, 30);
        Order order = new OrderBuilder().withDeliveryDate(date).build();
        assertEquals(order.getDeliveryDate(), date);
    }

    @Test
    public void testDeliveryHour() {
        LocalTime time = LocalTime.of(9, 30);
        Order order = new OrderBuilder().withDeliveryHour(time).build();
        assertEquals(order.getDeliveryHour(), time);
    }

    @Test
    public void testAmount() {
        Order order = new OrderBuilder().withAmount(200).build();
        assertEquals(order.getAmount(), 200);
    }


    /*
        End Test builder
     */


}




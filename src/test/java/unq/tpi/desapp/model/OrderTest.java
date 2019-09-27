package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.builders.ClientBuilder;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.OrderBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

import java.time.LocalDateTime;

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


    /*
        End Test builder
     */


}




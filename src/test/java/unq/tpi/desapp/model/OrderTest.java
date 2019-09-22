package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.OrderBuilder;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    /*
        Start Test builder
     */

    @Test
    public void testMenu(){
        Menu menu = new MenuBuilder().build();
        Order order = new OrderBuilder().withMenu(menu).build();
        assertEquals(order.getMenu(), menu);
    }

    @Test
    public void testCantidad(){
        Order order = new OrderBuilder().withCantidad(2).build();
        assertEquals(order.getCantidad(), 2);
    }

    @Test
    public void testFechaEntrega(){
        LocalDateTime fechaEntrega = LocalDateTime.now();
        Order order = new OrderBuilder().withFechaEntrega(fechaEntrega).build();
        assertEquals(order.getFechaEntrega(), fechaEntrega);
    }

    /*
        End Test builder
     */


}




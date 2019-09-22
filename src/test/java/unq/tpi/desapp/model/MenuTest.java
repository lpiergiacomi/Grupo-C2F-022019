package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    /*
        Start Test builder
     */

    @Test
    public void testName(){
        Menu menu = new MenuBuilder().withName("Tradicional").build();
        assertEquals(menu.getName(), "Tradicional");
    }

    @Test
    public void testDescription(){
        Menu menu = new MenuBuilder().withDescription("Menú tradicional").build();
        assertEquals(menu.getDescription(), "Menú tradicional");
    }

    @Test
    public void testDeliveryPrice(){
        Menu menu = new MenuBuilder().withDeliveryPrice(50).build();
        assertEquals(menu.getDeliveryPrice(), 50);
    }

    @Test
    public void testPrice(){
        Menu menu = new MenuBuilder().withPrice(150).build();
        assertEquals(menu.getPrice(), 150);
    }


    // TODO: Las cantidades mínimas deberán ser  mutuamente excluyentes entre los 2 segmentos.
    //  El precio *Min2 debe ser menor que *Min1 y este debe ser menor a Precio.
    //  No se permiten mas ventas que la cantidad máxima.


    @Test
    public void testMinQuantity(){
        Menu menu = new MenuBuilder().withMinQuantity(5).build();
        assertEquals(menu.getMinQuantity(), 5);
    }

    @Test
    public void testQuantityPrice(){
        Menu menu = new MenuBuilder().withQuantityPrice(100).build();
        assertEquals(menu.getMinQuantityPrice(), 100);
    }

    @Test
    public void testMinQuantity2(){
        Menu menu = new MenuBuilder().withMinQuantity2(2).build();
        assertEquals(menu.getMinQuantity2(), 2);
    }

    @Test
    public void testQuantityPrice2(){
        Menu menu = new MenuBuilder().withQuantityPrice2(90).build();
        assertEquals(menu.getMinQuantityPrice2(), 90);
    }

    @Test
    public void testMaxSalesPerDay(){
        Menu menu = new MenuBuilder().withMaxSalesPerDay(10).build();
        assertEquals(menu.getMaxSalesPerDay(), 10);
    }

    @Test
    public void testProvider(){
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withProvider(provider).build();
        assertEquals(menu.getProvider(), provider);
    }

    /*
        End Test builder
     */


}




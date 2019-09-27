package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.MenuOrderBuilder;

import static org.junit.Assert.assertEquals;

public class MenuOrderTest {

    /*
        Start Test builder
     */

    @Test
    public void testMenu() {
        Menu menu = new MenuBuilder().withName("Tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).build();
        assertEquals(menuOrder.getMenu(), menu);
    }

    @Test
    public void testQuantity() {
        MenuOrder menuOrder = new MenuOrderBuilder().withQuantity(1).build();
        assertEquals(menuOrder.getQuantity(), 1);
    }

    /*
        End Test builder
     */

    @Test
    public void totalAmountWithoutMinQuantityTest() {
        Menu menu = new MenuBuilder().withName("Tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(2).build();
        assertEquals(menuOrder.totalAmount(), 400);
    }

    @Test
    public void totalAmountWithMinQuantityTest() {
        Menu menu = new MenuBuilder().withName("Tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).withMinQuantity(2).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(2).build();
        assertEquals(menuOrder.totalAmount(), 300);
    }

    @Test
    public void totalAmountWithMinQuantity2Test() {
        Menu menu = new MenuBuilder().withName("Tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).withMinQuantity2(3).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(3).build();
        assertEquals(menuOrder.totalAmount(), 300);
        assertEquals(menuOrder.getPrice(), 300);
    }

}

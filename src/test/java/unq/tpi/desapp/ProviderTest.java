package unq.tpi.desapp;

import org.junit.Test;
import unq.tpi.desapp.exceptions.MaxMenusException;
import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.builders.MenuBuilder;
import unq.tpi.desapp.builders.MenuOrderBuilder;
import unq.tpi.desapp.builders.ProviderBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProviderTest {

    /*
        Start Test builder
     */

    @Test
    public void nameTest() {
        Provider viri = new ProviderBuilder().withName("Viri Burguer").build();
        assertEquals(viri.getName(), "Viri Burguer");
    }

    @Test
    public void logoTest() {
        Provider viri = new ProviderBuilder().withLogo("Logo").build();
        assertEquals(viri.getLogo(), "Logo");
    }

    @Test
    public void localityTest() {
        Provider viri = new ProviderBuilder().withLocality("Bernal").build();
        assertEquals(viri.getLocality(), "Bernal");
    }

    @Test
    public void addressTest() {
        Provider viri = new ProviderBuilder().withAddress("25 de Mayo 161").build();
        assertEquals(viri.getAddress(), "25 de Mayo 161");
    }

    @Test
    public void descriptionTest() {
        Provider viri = new ProviderBuilder().withDescription("Hamburgueseria").build();
        assertEquals(viri.getDescription(), "Hamburgueseria");
    }

    @Test
    public void siteTest() {
        Provider viri = new ProviderBuilder().withSite("viri.com.ar").build();
        assertEquals(viri.getSite(), "viri.com.ar");
    }

    @Test
    public void mailTest() {
        Provider viri = new ProviderBuilder().withMail("contacto@viri.com").build();
        assertEquals(viri.getMail(), "contacto@viri.com");
    }

    @Test
    public void phoneTest() {
        Provider viri = new ProviderBuilder().withPhone("123456789").build();
        assertEquals(viri.getPhone(), "123456789");
    }

    @Test
    public void attentionTimeBeginTest() {
        LocalTime time = LocalTime.of(9, 30);
        Provider viri = new ProviderBuilder().withAttentionTimeBegin(time).build();
        assertEquals(viri.getAttentionTimeBegin(), time);
    }

    @Test
    public void attentionTimeEndTest() {
        LocalTime time = LocalTime.of(9, 30);
        Provider viri = new ProviderBuilder().withAttentionTimeEnd(time).build();
        assertEquals(viri.getAttentionTimeEnd(), time);
    }

    @Test
    public void daysAttentionTest() {
        List<String> days = new ArrayList<>();
        days.add("Sábado");
        Provider viri = new ProviderBuilder().withDaysAttention(days).build();
        assertEquals(viri.getDaysAttention(), days);
    }

    @Test
    public void deliveryLocalitiesTest() {
        String locality = "Quilmes";
        List<String> deliveryLocalities = new ArrayList<>();
        deliveryLocalities.add(locality);
        Provider viri = new ProviderBuilder().withDeliveryLocalities(deliveryLocalities).build();
        assertEquals(viri.getDeliveryLocalities().get(0), "Quilmes");
    }

    @Test
    public void menusTest() {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new MenuBuilder().withDescription("Menú tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        menus.add(menu);
        Provider viri = new ProviderBuilder().withMenus(menus).build();
        assertEquals(viri.getMenus().get(0).getDescription(), "Menú tradicional");
    }

    @Test
    public void creditTest() {
        int credit = 100;
        Provider viri = new ProviderBuilder().withCredit(credit).build();
        assertEquals(viri.getCredit(), credit);
    }

    /*
        End Test builder
     */

    @Test
    public void addMenuTest() {
        List<Menu> menus = new ArrayList<>();
        Provider viri = new ProviderBuilder().withMenus(menus).build();
        Menu menu = new MenuBuilder().withDescription("Menu").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        viri.addMenu(menu);
        assertEquals(viri.getMenus().size(), 1);
    }

    @Test(expected = MaxMenusException.class)
    public void addMenuWithFullCapacityMenusTest() {
        List<Menu> mockMenus = mock(List.class);
        when(mockMenus.size()).thenReturn(20);
        Provider viri = new ProviderBuilder().withMenus(mockMenus).build();
        Menu menu = new MenuBuilder().withDescription("Menu").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        viri.addMenu(menu);
    }

    @Test
    public void increaseCreditTest() {
        Provider viri = new ProviderBuilder().withCredit(100).build();
        viri.increaseCredit(100);
        assertEquals(viri.getCredit(), 200);
    }

    @Test
    public void hasEnoughMenuTest() {
        Menu menu = new MenuBuilder().withDescription("Menu").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).withMaxSalesPerDay(2).build();
        MenuOrder menuOrder = new MenuOrderBuilder().withMenu(menu).withQuantity(1).build();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(menuOrder);
        Provider viri = new ProviderBuilder().withCredit(100).build();
        assertTrue(viri.hasEnoughMenu(menuOrders));
    }
}




package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPrice2Exception;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPriceException;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    /*
        Start Test builder
     */

    @Test
    public void testName() {
        Menu menu = new MenuBuilder().withName("Tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getName(), "Tradicional");
    }

    @Test
    public void testDescription() {
        Menu menu = new MenuBuilder().withDescription("Menú tradicional").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getDescription(), "Menú tradicional");
    }

    @Test
    public void testCategories() {
        List<MenuCategory> categories = new ArrayList<>();
        MenuCategory category = MenuCategory.Pizza;
        categories.add(category);
        Menu menu = new MenuBuilder().withCategories(categories).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getCategories().get(0), category);
    }

    @Test
    public void testDeliveryPrice() {
        Menu menu = new MenuBuilder().withDeliveryPrice(50).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getDeliveryPrice(), 50);
    }

    @Test
    public void testValidityDateBegin() {
        LocalDateTime date = LocalDateTime.of(2019, 07, 22, 9, 30);
        Menu menu = new MenuBuilder().withValidityDateBegin(date).withPrice(200).withQuantityPrice(150).build();
        assertEquals(menu.getValidityDateBegin(), date);
    }

    @Test
    public void testValidityDateEnd() {
        LocalDateTime date = LocalDateTime.of(2019, 07, 22, 9, 30);
        Menu menu = new MenuBuilder().withValidityDateEnd(date).withPrice(200).withQuantityPrice(150).build();
        assertEquals(menu.getValidityDateEnd(), date);
    }

    @Test
    public void testDeliveryTimeBegin() {
        LocalTime time = LocalTime.of(9, 30);
        Menu menu = new MenuBuilder().withDeliveryTimeBegin(time).withPrice(200).withQuantityPrice(150).build();
        assertEquals(menu.getDeliveryTimeBegin(), time);
    }

    @Test
    public void testDeliveryTimeEnd() {
        LocalTime time = LocalTime.of(10, 30);
        Menu menu = new MenuBuilder().withDeliveryTimeEnd(time).withPrice(200).withQuantityPrice(150).build();
        assertEquals(menu.getDeliveryTimeEnd(), time);
    }

    @Test
    public void testPrice() {
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getPrice(), 200);
    }

    @Test
    public void testMinQuantity() {
        Menu menu = new MenuBuilder().withMinQuantity(5).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getMinQuantity(), 5);
    }

    @Test
    public void testMinQuantityPrice() {
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getMinQuantityPrice(), 150);
    }

    @Test(expected = InvalidMinQuantityPriceException.class)
    public void testInvalidMinQuantityPrice() {
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(300).withQuantityPrice2(400).build();
        assertEquals(menu.getMinQuantityPrice(), 300);
    }

    @Test
    public void testMinQuantity2() {
        Menu menu = new MenuBuilder().withMinQuantity2(2).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getMinQuantity2(), 2);
    }

    @Test
    public void testMinQuantityPrice2() {
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getMinQuantityPrice2(), 100);
    }

    @Test(expected = InvalidMinQuantityPrice2Exception.class)
    public void testInvalidMinQuantityPrice2() {
        Menu menu = new MenuBuilder().withPrice(200).withQuantityPrice(150).withQuantityPrice2(180).build();
        assertEquals(menu.getMinQuantityPrice2(), 180);
    }

    @Test
    public void testMaxSalesPerDay() {
        Menu menu = new MenuBuilder().withMaxSalesPerDay(10).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getMaxSalesPerDay(), 10);
    }

    @Test
    public void testQualification() {
        Menu menu = new MenuBuilder().withQualification(10).withPrice(200).withQuantityPrice(150).build();
        assertEquals(menu.getQualification(), 10);
    }

    @Test
    public void testProvider() {
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withProvider(provider).withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).build();
        assertEquals(menu.getProvider(), provider);
    }

    /*
        End Test builder
     */


}




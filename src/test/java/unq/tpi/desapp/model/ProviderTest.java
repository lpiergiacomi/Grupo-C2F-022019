package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.exceptions.MaxMenusException;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProviderTest {

    /*
        Start Test builder
     */

    @Test
    public void testName() {
        Provider viri = new ProviderBuilder().withName("Viri Burguer").build();
        assertEquals(viri.getName(), "Viri Burguer");
    }

    @Test
    public void testLogo() {
        Provider viri = new ProviderBuilder().withLogo("Logo").build();
        assertEquals(viri.getLogo(), "Logo");
    }

    @Test
    public void testLocality() {
        Provider viri = new ProviderBuilder().withLocality("Bernal").build();
        assertEquals(viri.getLocality(), "Bernal");
    }

    @Test
    public void testAddress() {
        Provider viri = new ProviderBuilder().withAddress("25 de Mayo 161").build();
        assertEquals(viri.getAddress(), "25 de Mayo 161");
    }

    @Test
    public void testDescription() {
        Provider viri = new ProviderBuilder().withDescription("Hamburgueseria").build();
        assertEquals(viri.getDescription(), "Hamburgueseria");
    }

    @Test
    public void testSite() {
        Provider viri = new ProviderBuilder().withSite("viri.com.ar").build();
        assertEquals(viri.getSite(), "viri.com.ar");
    }

    @Test
    public void testMail() {
        Provider viri = new ProviderBuilder().withMail("contacto@viri.com").build();
        assertEquals(viri.getMail(), "contacto@viri.com");
    }

    @Test
    public void testPhone() {
        Provider viri = new ProviderBuilder().withPhone("123456789").build();
        assertEquals(viri.getPhone(), "123456789");
    }

    @Test
    public void testAttentionTimeBegin() {
        LocalTime time = LocalTime.of(9, 30);
        Provider viri = new ProviderBuilder().withAttentionTimeBegin(time).build();
        assertEquals(viri.getAttentionTimeBegin(), time);
    }

    @Test
    public void testAttentionTimeEnd() {
        LocalTime time = LocalTime.of(9, 30);
        Provider viri = new ProviderBuilder().withAttentionTimeEnd(time).build();
        assertEquals(viri.getAttentionTimeEnd(), time);
    }

    @Test
    public void testAttentionDayBegin() {
        DayOfWeek day = DayOfWeek.SATURDAY;
        Provider viri = new ProviderBuilder().withAttentionDayBegin(day).build();
        assertEquals(viri.getAttentionDayBegin(), day);
    }

    @Test
    public void testAttentionDayEnd() {
        DayOfWeek day = DayOfWeek.SATURDAY;
        Provider viri = new ProviderBuilder().withAttentionDayEnd(day).build();
        assertEquals(viri.getAttentionDayEnd(), day);
    }


    @Test
    public void testMenus() {
        List<Menu> menus = new ArrayList();
        Provider viri = new ProviderBuilder().withMenus(menus).build();
        assertEquals(viri.getMenus(), menus);
    }

    /*
        End Test builder
     */

    @Test
    public void addMenuTest() {
        List<Menu> menus = new ArrayList();
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
}




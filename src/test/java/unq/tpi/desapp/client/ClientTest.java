package unq.tpi.desapp.client;

import org.junit.Before;
import org.junit.Test;
import unq.tpi.desapp.models.Client;
import unq.tpi.desapp.models.Menu;
import unq.tpi.desapp.models.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ClientTest {

    private Client client1;
    private Menu menu;
    private Service service;

    @Before
    public void prepare() {
        this.client1 = new Client("Esteban", "Matas", "estebanmatas13@gmail.com", "1122334455", "Quilmes", "Av Calchaqui 666");
        this.menu = new Menu("Tradicional", "Menu tradicional", 3, 450, 10);
        this.service = new Service("Viri", "Bernal", "25 de Mayo 161", "Viri Burger", "viriburger@gmail.com", "2122 1000", "Todos los dias");
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        service.setMenus(menus);
        List<Service> services = new ArrayList<>();
        services.add(service);
        client1.setServices(services);
    }

    @Test
    public void searchMenuTest() {
        List<Menu> menuResults = client1.searchMenu("Tradicional");
        assertTrue(menuResults.size() > 0);
        assertTrue(menuResults.contains(menu));
    }
}

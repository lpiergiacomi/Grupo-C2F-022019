package unq.tpi.desapp.client;

import org.junit.Before;
import org.junit.Test;
import unq.tpi.desapp.models.Client;
import unq.tpi.desapp.models.Menu;
import unq.tpi.desapp.models.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    private Client client1;
    private Menu menu1;
    private Menu menu2;
    private Menu menu3;
    private Service service1;
    private Service service2;

    @Before
    public void prepare() {
        this.client1 = new Client("Esteban", "Matas", "estebanmatas13@gmail.com", "1122334455", "Quilmes", "Av Calchaqui 666");

        this.menu1 = new Menu("Tradicional", "Menu tradicional", 3, 450, 10);
        this.menu1.setPrice(150);

        this.menu2 = new Menu("Papas Cheddar", "Menu papas cheddar", 2, 210, 5);
        this.menu2.setPrice(200);

        this.menu3 = new Menu("Pizza", "Menu Pizza", 5, 475, 20);
        this.menu3.setPrice(100);

        this.service1 = new Service("Viri", "Bernal", "25 de Mayo 161", "Viri Burger", "viriburger@gmail.com", "2122 1000", "Todos los dias");
        this.service2 = new Service("Antares", "Quilmes", "Colon 215", "Antares Quilmes", "antaresquilmes@gmail.com", "4224 5207", "Siempre");

        List<Menu> menusService1 = new ArrayList<>();
        List<Menu> menusService2 = new ArrayList<>();

        menusService1.add(menu1);
        menusService2.add(menu2);
        menusService2.add(menu3);

        service1.setMenus(menusService1);
        service2.setMenus(menusService2);

        List<Service> services = new ArrayList<>();

        services.add(service1);
        services.add(service2);

        client1.setServices(services);
    }

    @Test
    public void searchMenuNameTest() {
        //List<Menu> menuResults = client1.searchMenuName("Tradicional");
        client1.searchMenuName("Tradicional");
        assertTrue(client1.getMenus().size() > 0);
        assertTrue(client1.getMenus().contains(menu1));
    }

    @Test
    public void searchMenuNameLocalityTest() {
        //List<Menu> menuResults = client1.searchMenuLocality("Quilmes");
        client1.searchMenuLocality("Quilmes");
        assertTrue(client1.getMenus().size() > 0);
        assertTrue(client1.getMenus().contains(menu2));
    }

    @Test
    public void sortMenusByPrice() {
        client1.searchMenuLocality("Quilmes");
        client1.sortMenusByPrice();
        assertEquals(client1.getMenus().get(0).getPrice(), 100);
    }
}




package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.exceptions.CreditoInsuficiente;
import unq.tpi.desapp.exceptions.FechaDeEntregaInvalida;
import unq.tpi.desapp.exceptions.VentasDeMenuExcedidas;
import unq.tpi.desapp.model.builders.ClientBuilder;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ClientTest {

    /*
        Start Test builder
     */

    @Test
    public void testFirstName(){
        Client esteban = new ClientBuilder().withFirstName("Esteban").build();
        assertEquals(esteban.getFirstName(), "Esteban");
    }

    @Test
    public void testLastName(){
        Client esteban = new ClientBuilder().withLastName("Matas").build();
        assertEquals(esteban.getLastName(), "Matas");
    }

    @Test
    public void testMail(){
        Client esteban = new ClientBuilder().withMail("estebanmatas13@gmail.com").build();
        assertEquals(esteban.getMail(), "estebanmatas13@gmail.com");
    }

    @Test
    public void testPhone(){
        Client esteban = new ClientBuilder().withPhone("1122334455").build();
        assertEquals(esteban.getPhone(), "1122334455");
    }

    @Test
    public void testLocality(){
        Client esteban = new ClientBuilder().withLocality("Quilmes").build();
        assertEquals(esteban.getLocality(), "Quilmes");
    }

    @Test
    public void testAddress(){
        Client esteban = new ClientBuilder().withAddress("Av Calchaqui 666").build();
        assertEquals(esteban.getAddress(), "Av Calchaqui 666");
    }

    @Test
    public void testCredit(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        assertEquals(esteban.getCredit(), 150);
    }

    /*
        End Test builder
     */

    @Test
    public void testDepositarCredito(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.depositarCredito(200);
        assertEquals(esteban.getCredit(), 350);
    }

    @Test
    public void testRetirarCredito(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.retirarCredito(50);
        assertEquals(esteban.getCredit(), 100);
    }

    @Test
    public void testNoTieneCreditoParaMenu(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        Menu menu = new MenuBuilder().withPrice(200).build();
        assertFalse(esteban.tieneCreditoParaMenu(menu, 1));
    }


    @Test (expected = FechaDeEntregaInvalida.class)
    public void crearOrdenConMenosDe48Horas(){
        LocalDateTime fechaDeEntrega = LocalDateTime.now().plusDays(1);
        Client esteban = new ClientBuilder().build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().build();

        esteban.createOrder(provider, menu, 10, fechaDeEntrega);
    }

    @Test (expected = VentasDeMenuExcedidas.class)
    public void crearOrdenSinSuficientesMenus(){
        LocalDateTime fechaDeEntrega = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(9).build();

        esteban.createOrder(provider, menu, 10, fechaDeEntrega);
    }

    @Test (expected = CreditoInsuficiente.class)
    public void crearOrdenSinSuficienteCredito(){
        LocalDateTime fechaDeEntrega = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(90).build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(100).build();
        esteban.createOrder(provider, menu, 10, fechaDeEntrega);
    }

    @Test
    public void crearOrdenValida(){
        LocalDateTime fechaDeEntrega = LocalDateTime.now().plusDays(10);
        Client esteban = new ClientBuilder().withCredit(1000).build();
        Provider provider = new ProviderBuilder().build();
        Menu menu = new MenuBuilder().withMaxSalesPerDay(99).withPrice(100).build();
        assertEquals(esteban.getOrders().size(), 0);
        esteban.createOrder(provider, menu, 10, fechaDeEntrega);
        assertEquals(esteban.getOrders().size(), 1);
    }

}




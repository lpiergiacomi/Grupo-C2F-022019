package unq.tpi.desapp;

import org.junit.Test;
import unq.tpi.desapp.exceptions.CreditoInsuficiente;
import unq.tpi.desapp.models.Client;
import unq.tpi.desapp.models.builders.ClientBuilder;

import static org.junit.Assert.assertEquals;

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
        esteban.depositar(200);
        assertEquals(esteban.getCredit(), 350);
    }

    @Test
    public void testRetirarCredito(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.retirar(50);
        assertEquals(esteban.getCredit(), 100);
    }

    @Test (expected = CreditoInsuficiente.class)
    public void testRetirarMasCreditoDelQueHayDisponible(){
        Client esteban = new ClientBuilder().withCredit(150).build();
        esteban.retirar(200);
        assertEquals(esteban.getCredit(), 150);
    }

}




package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.exceptions.InvalidEmailException;

import static org.junit.Assert.assertEquals;

public class EmailTest {

    @Test
    public void validEMailTest() {
        Email email = new Email();
        email.validEmail(email.getUser());
    }

    @Test(expected = InvalidEmailException.class)
    public void invalidEmailTest() {
        Email email = new Email();
        email.setUser("estebanmatas@gmail");
        email.validEmail(email.getUser());
    }

    @Test
    public void createEmailWithTest() {
        Email email = new Email();
        email.createEmailWith("Prueba", "lgpiergiacomi@gmail.com", "Mensaje de prueba");
        assertEquals(email.getSubject(), "Prueba");
        assertEquals(email.getReceiver(), "lgpiergiacomi@gmail.com");
        assertEquals(email.getMessage(), "Mensaje de prueba");
    }
}

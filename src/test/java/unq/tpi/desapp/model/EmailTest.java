package unq.tpi.desapp.model;

import org.junit.Test;
import unq.tpi.desapp.controllers.EmailSender;
import unq.tpi.desapp.exceptions.InvalidEmailException;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EmailTest {

    @Test
    public void validEMailTest() {
        Email email = new Email();
        email.validEmail(email.getUser());
        assertEquals(email.getUser(), "contacto.viandasya@gmail.com");
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

    @Test
    public void sendEmailTest() {
        Email email = new Email();
        email.createEmailWith("Prueba", "estebanmatas13@gmail.com", "Mensaje de prueba");

        EmailSender emailSender = mock(EmailSender.class);

        emailSender.sendEmail(email);

        verify(emailSender, times(1)).sendEmail(email);
    }

}
package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.InvalidEmailException;

@Getter
@Setter
public class Email {

    private String user;
    private String password;
    private String subject;
    private String receiver;
    private String message;

    public Email() {

        this.user = "contacto.viandasya@gmail.com";
        this.password = "viandas1234";
    }

    public void createEmailWith(String subject, String receiver, String message) {
        // Chequea si un mail es valido y lo compone con los campos que recibe como parametro.
        validEmail(receiver);

        setSubject(subject);
        setReceiver(receiver);
        setMessage(message);
    }

    private void validEmail(String newEmail) {
        // Chequea si una mail es valido mediante expresiones regulares.
        if (!newEmail.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidEmailException("Invalid email");
    }

}
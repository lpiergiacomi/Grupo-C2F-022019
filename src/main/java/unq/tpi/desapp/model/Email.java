package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;

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
        setSubject(subject);
        setReceiver(receiver);
        setMessage(message);
    }

}
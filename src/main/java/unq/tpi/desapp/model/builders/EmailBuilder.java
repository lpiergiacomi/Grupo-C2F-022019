package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.Email;

public class EmailBuilder {

    private String user;
    private String password;
    private String subject;
    private String receiver;
    private String message;

    public EmailBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public EmailBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public EmailBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder withReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public EmailBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public Email build() {
        return new Email();
    }
}

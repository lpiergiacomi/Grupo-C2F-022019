package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.Client;

public class ClientBuilder {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String locality;
    private String address;
    private int credit;

    public ClientBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ClientBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ClientBuilder withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public ClientBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder withCredit(int credit) {
        this.credit = credit;
        return this;
    }

    public Client build() {
        return new Client(firstName, lastName, mail, phone, locality, address, credit);
    }
}

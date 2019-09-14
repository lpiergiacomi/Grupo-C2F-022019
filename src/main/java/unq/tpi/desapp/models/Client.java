package unq.tpi.desapp.models;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.CreditoInsuficiente;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Client {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String locality;
    private String address;
    private int credit;
    private List<Menu> menus;

    public Client(String firstName, String lastName, String mail, String phone, String locality, String address, int credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.locality = locality;
        this.address = address;
        this.credit = credit;
        this.menus = new ArrayList<Menu>();
    }

    public void depositar(int credito){
        this.credit += credito;
    }

    public void retirar(int credito){
        if (this.credit >= credito){
            this.credit -= credito;
        }
        else {
            throw new CreditoInsuficiente("No tenés creditos");
        }
    }
}

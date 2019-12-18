package unq.tpi.desapp.menu;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.email.Email;
import unq.tpi.desapp.email.EmailSender;
import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.model.Provider;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class MenuOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Menu menu;
    private int quantity;
    private int price;
    private String deliveryType;
    private LocalDateTime deliveryDate;
    private String deliveryTime;
    private Long idClient;
    private int qualification;


    public MenuOrder() {}

    public MenuOrder(Menu menu, int quantity, String deliveryType, LocalDateTime deliveryDate, String deliveryTime)
    {
        this.menu = menu;
        this.quantity = quantity;
        this.price = 0;
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
    }

    public int totalAmount() {
        if (this.getMenu().getMinQuantity() == getQuantity()) {
            this.price += this.getMenu().getMinQuantityPrice() * this.getQuantity();
        } else if (this.getMenu().getMinQuantity2() == getQuantity()) {
            this.price += this.getMenu().getMinQuantityPrice2() * this.getQuantity();
        } else {
            this.price += this.getMenu().getPrice() * this.getQuantity();
        }

        return this.price;
    }


    public void sendConfirmationEmails(Client client, Provider provider) {
        // El mail deberia ademas informar el tiempo de entrega o tiempo de retiro.
        Email emailClient = new Email();
        Email emailProvider = new Email();

        emailClient.createEmailWith("Confirmación de compra con ViandasYa",client.getMail(), "Su compra fue realizada exitosamente!");

        emailProvider.createEmailWith("Confirmación de venta con ViandasYa", provider.getMail(), "La venta fue realizada exitosamente!");

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail(emailClient);
        emailSender.sendEmail(emailProvider);
    }


    public boolean hasEnoughMenu() {
        return this.getMenu().getMaxSalesPerDay() >= this.getQuantity();
    }
}

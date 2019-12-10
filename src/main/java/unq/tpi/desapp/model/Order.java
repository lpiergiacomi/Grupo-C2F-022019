package unq.tpi.desapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.model.email.Email;
import unq.tpi.desapp.model.email.EmailSender;
import unq.tpi.desapp.model.menu.MenuOrder;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Provider provider;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    @ElementCollection
    private List<MenuOrder> menuOrders;
    private String deliveryType;
    private LocalDateTime deliveryDate;
    private LocalTime deliveryHour;
    private int amount;


    public Order() {}

    public Order(Provider provider, Client client, List<MenuOrder> menuOrders, String deliveryType, LocalDateTime deliveryDate, LocalTime deliveryHour, int amount) {
        this.provider = provider;
        this.client = client;
        this.menuOrders = menuOrders;
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.deliveryHour = deliveryHour;
        this.amount = amount;
    }

    public Order(Provider provider, Client client, LocalDateTime deliveryDate) {
        this.provider = provider;
        this.client = client;
        this.menuOrders = new ArrayList<>();
        this.deliveryDate = deliveryDate;
        this.amount = 0;
    }

    public int totalMenus() {
        return menuOrders.stream()
                .mapToInt(m -> m.getQuantity())
                .sum();
    }

    public void decreaseClientCredit(int credit) {

        this.getClient().discountCredit(credit);
    }

    public void increaseProviderCredit(int credit) {
        this.getProvider().increaseCredit(credit);
    }


    public void sendConfirmationEmails() {
        // El mail deberia ademas informar el tiempo de entrega o tiempo de retiro.
        Email emailClient = new Email();
        Email emailProvider = new Email();

        emailClient.createEmailWith("Confirmación de compra con ViandasYa", this.getClient().getMail(), "Su compra fue realizada exitosamente!");

        emailProvider.createEmailWith("Confirmación de venta con ViandasYa", this.getProvider().getMail(), "La venta fue realizada exitosamente!");

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail(emailClient);
        emailSender.sendEmail(emailProvider);
    }


}
package unq.tpi.desapp.menu;

import lombok.Getter;
import lombok.Setter;
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

    public boolean hasEnoughMenu() {
        return this.getMenu().getMaxSalesPerDay() >= this.getQuantity();
    }
}

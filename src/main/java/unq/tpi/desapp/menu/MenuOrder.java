package unq.tpi.desapp.menu;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public MenuOrder() {}

    public MenuOrder(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = 0;
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
}

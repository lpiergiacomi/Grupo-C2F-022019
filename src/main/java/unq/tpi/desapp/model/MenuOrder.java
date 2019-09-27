package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOrder {

    private Menu menu;
    private int quantity;
    private int price;

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
        //TODO
        // Falta el caso que la cantidad que quiere comprar el cliente es mayor a minQuantityPrice o
        // minQuantityPrice2, deberia mejorarse el precio.

        return this.price;
    }
}

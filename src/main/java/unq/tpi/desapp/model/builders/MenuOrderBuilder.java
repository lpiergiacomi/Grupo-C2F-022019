package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.Menu;
import unq.tpi.desapp.model.MenuOrder;

public class MenuOrderBuilder {

    private Menu menu;
    private int quantity;
    private int price;


    public MenuOrderBuilder withMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public MenuOrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public MenuOrderBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public MenuOrder build() {
        return new MenuOrder(menu, quantity);
    }

}

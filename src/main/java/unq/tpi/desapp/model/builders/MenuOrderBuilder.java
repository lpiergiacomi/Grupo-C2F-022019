package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.model.menu.MenuOrder;

public class MenuOrderBuilder {

    private Menu menu;
    private int quantity;


    public MenuOrderBuilder withMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public MenuOrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }


    public MenuOrder build() {
        return new MenuOrder(menu, quantity);
    }

}

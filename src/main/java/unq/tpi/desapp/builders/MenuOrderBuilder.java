package unq.tpi.desapp.builders;

import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Provider;

import java.time.LocalDateTime;

public class MenuOrderBuilder {

    private Menu menu;
    private int quantity;
    private String deliveryType;
    private LocalDateTime deliveryDate;
    private String deliveryTime;


    public MenuOrderBuilder withMenu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public MenuOrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public MenuOrderBuilder withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public MenuOrderBuilder withDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public MenuOrderBuilder withDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public MenuOrder build() {
        return new MenuOrder(menu, quantity, deliveryType, deliveryDate, deliveryTime);
    }

}

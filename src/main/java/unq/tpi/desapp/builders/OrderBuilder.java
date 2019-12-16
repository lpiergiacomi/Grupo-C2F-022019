package unq.tpi.desapp.builders;

import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Order;
import unq.tpi.desapp.model.Provider;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OrderBuilder {

    private Provider provider;
    private Client client;
    private List<MenuOrder> menuOrders;
    private String deliveryType;
    private LocalDateTime deliveryDate;
    private LocalTime deliveryHour;
    private int amount;

    public OrderBuilder withProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public OrderBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public OrderBuilder withMenuOrders(List<MenuOrder> menuOrders) {
        this.menuOrders = menuOrders;
        return this;
    }

    public OrderBuilder withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    public OrderBuilder withDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public OrderBuilder withDeliveryHour(LocalTime deliveryHour) {
        this.deliveryHour = deliveryHour;
        return this;
    }

    public OrderBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }


    public Order build() {
        return new Order(provider, client, menuOrders, deliveryType, deliveryDate, deliveryHour, amount);
    }
}
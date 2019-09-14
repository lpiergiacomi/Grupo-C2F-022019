package unq.tpi.desapp.models.builders;

import unq.tpi.desapp.models.Menu;
import unq.tpi.desapp.models.Provider;

import java.util.List;

public class MenuBuilder {

    private String name;
    private String description;
    //private Category category;
    private int deliveryPrice;
    private int price;
    private int minQuantity;
    private int minQuantityPrice;
    private int minQuantity2;
    private int minQuantityPrice2;
    private int maxSalesPerDay;


    public MenuBuilder withName(String name){
        this.name = name;
        return this;
    }

    public MenuBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public MenuBuilder withDeliveryPrice(int deliveryPrice){
        this.deliveryPrice = deliveryPrice;
        return this;
    }

    public MenuBuilder withPrice(int price){
        this.price = price;
        return this;
    }

    public MenuBuilder withMinQuantity(int minQuantity){
        this.minQuantity = minQuantity;
        return this;
    }

    public MenuBuilder withQuantityPrice(int minQuantityPrice){
        this.minQuantityPrice = minQuantityPrice;
        return this;
    }

    public MenuBuilder withMinQuantity2(int minQuantity2){
        this.minQuantity2 = minQuantity2;
        return this;
    }

    public MenuBuilder withQuantityPrice2(int minQuantityPrice2){
        this.minQuantityPrice2 = minQuantityPrice2;
        return this;
    }

    public MenuBuilder withMaxSalesPerDay(int maxSalesPerDay){
        this.maxSalesPerDay = maxSalesPerDay;
        return this;
    }



    public Menu build() {
            return new Menu(name, description, deliveryPrice, price, minQuantity, minQuantityPrice, minQuantity2, minQuantityPrice2, maxSalesPerDay);
    }
}

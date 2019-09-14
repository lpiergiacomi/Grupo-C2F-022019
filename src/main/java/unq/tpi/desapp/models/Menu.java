package unq.tpi.desapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {

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

    public Menu(String name, String description, int deliveryPrice, int price, int minQuantity, int minQuantityPrice, int minQuantity2, int minQuantityPrice2,int maxSalesPerDay) {
        this.name = name;
        this.description = description;
        this.deliveryPrice = deliveryPrice;
        this.price = price;
        this.minQuantity = minQuantity;
        this.minQuantityPrice = minQuantityPrice;
        this.minQuantity2 = minQuantity2;
        this.minQuantityPrice2 = minQuantityPrice2;
        this.maxSalesPerDay = maxSalesPerDay;

    }
}

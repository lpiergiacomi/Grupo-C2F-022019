package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.model.menu.MenuCategory;
import unq.tpi.desapp.model.Provider;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MenuBuilder {

    private String name;
    private String description;
    private List<MenuCategory> categories;
    private int deliveryPrice;
    private LocalDateTime validityDateBegin;
    private LocalDateTime validityDateEnd;
    private LocalTime deliveryTimeBegin;
    private LocalTime deliveryTimeEnd;
    private int deliveryTimeAverage;
    private int preparationTime;
    private int price;
    private int minQuantity;
    private int minQuantityPrice;
    private int minQuantity2;
    private int minQuantityPrice2;
    private int maxSalesPerDay;
    private int qualification;
    private Provider provider;


    public MenuBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MenuBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MenuBuilder withCategories(List<MenuCategory> categories) {
        this.categories = categories;
        return this;
    }

    public MenuBuilder withDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
        return this;
    }

    public MenuBuilder withValidityDateBegin(LocalDateTime validityDateBegin) {
        this.validityDateBegin = validityDateBegin;
        return this;
    }

    public MenuBuilder withValidityDateEnd(LocalDateTime validityDateEnd) {
        this.validityDateEnd = validityDateEnd;
        return this;
    }

    public MenuBuilder withDeliveryTimeBegin(LocalTime deliveryTimeBegin) {
        this.deliveryTimeBegin = deliveryTimeBegin;
        return this;
    }

    public MenuBuilder withDeliveryTimeEnd(LocalTime deliveryTimeEnd) {
        this.deliveryTimeEnd = deliveryTimeEnd;
        return this;
    }

    public MenuBuilder withDeliveryTimeAverage(int deliveryTimeAverage) {
        this.deliveryTimeAverage = deliveryTimeAverage;
        return this;
    }

    public MenuBuilder withPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
        return this;
    }

    public MenuBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public MenuBuilder withMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
        return this;
    }

    public MenuBuilder withQuantityPrice(int minQuantityPrice) {
        this.minQuantityPrice = minQuantityPrice;
        return this;
    }

    public MenuBuilder withMinQuantity2(int minQuantity2) {
        this.minQuantity2 = minQuantity2;
        return this;
    }

    public MenuBuilder withQuantityPrice2(int minQuantityPrice2) {
        this.minQuantityPrice2 = minQuantityPrice2;
        return this;
    }

    public MenuBuilder withMaxSalesPerDay(int maxSalesPerDay) {
        this.maxSalesPerDay = maxSalesPerDay;
        return this;
    }

    public MenuBuilder withQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public MenuBuilder withProvider(Provider provider) {
        this.provider = provider;
        return this;
    }


    public Menu build() {
        return new Menu(name, description, categories, deliveryPrice, validityDateBegin, validityDateEnd, deliveryTimeBegin, deliveryTimeEnd, deliveryTimeAverage, preparationTime, price, minQuantity, minQuantityPrice, minQuantity2, minQuantityPrice2, maxSalesPerDay, qualification, provider);
    }
}

package unq.tpi.desapp.model.menu;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPrice2Exception;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPriceException;
import unq.tpi.desapp.model.Provider;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
    private String description;
    @ElementCollection
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
    @OneToOne
    private Provider provider;



    public Menu(String name, String description, List<MenuCategory> categories, int deliveryPrice, LocalDateTime validityDateBegin, LocalDateTime validityDateEnd, LocalTime deliveryTimeBegin, LocalTime deliveryTimeEnd, int deliveryTimeAverage, int preparationTime, int price, int minQuantity, int minQuantityPrice, int minQuantity2, int minQuantityPrice2, int maxSalesPerDay, int qualification, Provider provider) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.deliveryPrice = deliveryPrice;
        this.validityDateBegin = validityDateBegin;
        this.validityDateEnd = validityDateEnd;
        this.deliveryTimeBegin = deliveryTimeBegin;
        this.deliveryTimeEnd = deliveryTimeEnd;
        this.deliveryTimeAverage = deliveryTimeAverage;
        this.preparationTime = preparationTime;
        this.price = price;
        this.minQuantity = minQuantity;
        this.minQuantity2 = minQuantity2;
        this.maxSalesPerDay = maxSalesPerDay;
        this.qualification = qualification;
        this.provider = provider;

        setMinQuantityPrice(minQuantityPrice);
        setMinQuantityPrice2(minQuantityPrice2);

    }

    public Menu() {
    }

    public void setMinQuantityPrice(int minPrice) {
        if (minPrice < this.getPrice()) {
            this.minQuantityPrice = minPrice;
        } else {
            throw new InvalidMinQuantityPriceException("El precio minimo debe ser menor al precio");
        }
    }

    public void setMinQuantityPrice2(int minPrice) {
        if (minPrice < this.getMinQuantityPrice()) {
            this.minQuantityPrice2 = minPrice;
        } else {
            throw new InvalidMinQuantityPrice2Exception("El precio minimo 2 debe ser menor al precio minimo");
        }

    }

}

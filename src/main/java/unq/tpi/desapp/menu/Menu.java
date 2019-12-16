package unq.tpi.desapp.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPrice2Exception;
import unq.tpi.desapp.exceptions.InvalidMinQuantityPriceException;
import unq.tpi.desapp.model.Provider;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 4, max = 30, message = "tiene que tener entre 4 y 30 caracteres")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 20, max = 40, message = "tiene que tener entre 20 y 40 caracteres")
    @Column(nullable = false)
    private String description;

    @NotEmpty
    @ElementCollection
    private List<MenuCategory> categories;


    //TODO: Es un campo opcional, si no escriben nada llega null, y no pasa las validaciones de arriba
    private int deliveryPrice;

    @NotNull(message = "no puede estar vacío")
    @Temporal(TemporalType.DATE)
    private Date validityDateBegin;

    @NotNull(message = "no puede estar vacío")
    @Temporal(TemporalType.DATE)
    private Date validityDateEnd;

    //@Temporal(TemporalType.DATE)
    private LocalTime deliveryTimeBegin;

    //@Temporal(TemporalType.DATE)
    private LocalTime deliveryTimeEnd;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 0, message = "no puede ser menor a 0")
    //TODO: DEBERIA SER UN TIMEPICKER?
    private int deliveryTimeAverage;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 0, message = "no puede ser menor a 0")
    private int price;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 10, message = "no puede ser menor a 10")
    @Max(value = 70, message = "no puede ser mayor a 70")
    private int minQuantity;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 0, message = "no puede ser menor a 0")
    @Max(value = 1000, message = "no puede ser mayor a 1000")
    // DEBE SER MENOR A PRECIO
    private int minQuantityPrice;

    private int minQuantity2;

    // DEBE SER MENOR A PRECIO MINIMO 1
    private int minQuantityPrice2;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 0, message = "no puede ser menor a 0")
    private int maxSalesPerDay;

    private int preparationTime;

    private int qualification;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    @JsonBackReference
    private Provider provider = new Provider();

    public Menu() {
    }

    public Menu(String name, String description, List<MenuCategory> categories, int deliveryPrice, Date validityDateBegin, Date validityDateEnd, LocalTime deliveryTimeBegin, LocalTime deliveryTimeEnd, int deliveryTimeAverage, int preparationTime, int price, int minQuantity, int minQuantityPrice, int minQuantity2, int minQuantityPrice2, int maxSalesPerDay, int qualification, Provider provider) {
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

    public Long getIdProvider() {
        return this.provider.getId();
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

    public void setDeliveryPrice(int deliveryPrice){
        if (deliveryPrice != 0){
            if (deliveryPrice < 10 || deliveryPrice > 40){
                throw new InvalidMinQuantityPrice2Exception("El precio de delivery debe ser entre 10 y 40");
            }
        }
    }

}

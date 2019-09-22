package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Menu {

    private String name;
    private String description;
    //private Category category;
    private int deliveryPrice;
    private LocalDateTime fechaVigenciaDesde;
    private LocalDateTime fechaVigenciaHasta;
    // Turnos/Horarios de entrega/Envio
    // Tiempo promedio de entrega [Obligatorio]
    private int price;
    private int minQuantity;
    private int minQuantityPrice;
    private int minQuantity2;
    private int minQuantityPrice2;
    private int maxSalesPerDay;
    private Provider provider;

    public Menu(String name, String description, int deliveryPrice, int price, int minQuantity, int minQuantityPrice, int minQuantity2, int minQuantityPrice2,int maxSalesPerDay, Provider provider) {
        this.name = name;
        this.description = description;
        this.deliveryPrice = deliveryPrice;
        this.price = price;
        this.minQuantity = minQuantity;
        this.minQuantityPrice = minQuantityPrice;
        this.minQuantity2 = minQuantity2;
        this.minQuantityPrice2 = minQuantityPrice2;
        this.maxSalesPerDay = maxSalesPerDay;
        this.provider = provider;

    }
}

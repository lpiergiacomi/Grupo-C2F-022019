package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Order {

    private Menu menu;
    private int cantidad;
    //private String tipoEntrega;
    private LocalDateTime fechaEntrega;


    public Order(Menu menu, int cantidad, LocalDateTime fechaEntrega){
        this.menu = menu;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }

}
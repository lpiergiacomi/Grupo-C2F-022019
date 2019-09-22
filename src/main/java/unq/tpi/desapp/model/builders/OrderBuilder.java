package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.Menu;
import unq.tpi.desapp.model.Order;

import java.time.LocalDateTime;

public class OrderBuilder {

    private Menu menu;
    private int cantidad;
    private LocalDateTime fechaEntrega;


    public OrderBuilder withMenu(Menu menu){
        this.menu = menu;
        return this;
    }

    public OrderBuilder withCantidad(int cantidad){
        this.cantidad = cantidad;
        return this;
    }

    public OrderBuilder withFechaEntrega(LocalDateTime fechaEntrega){
        this.fechaEntrega = fechaEntrega;
        return this;
    }


    public Order build(){
        return new Order(menu, cantidad, fechaEntrega);
    }
}

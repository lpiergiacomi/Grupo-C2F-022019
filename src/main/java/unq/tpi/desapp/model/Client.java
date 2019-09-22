package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.CreditoInsuficiente;
import unq.tpi.desapp.exceptions.FechaDeEntregaInvalida;
import unq.tpi.desapp.exceptions.VentasDeMenuExcedidas;
import unq.tpi.desapp.model.builders.OrderBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Client {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String locality;
    private String address;
    private int credit;
    private List<Menu> menus;
    private List<Order> orders;

    public Client(String firstName, String lastName, String mail, String phone, String locality, String address, int credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.locality = locality;
        this.address = address;
        this.credit = credit;
        this.menus = new ArrayList<Menu>();
        this.orders = new ArrayList<Order>();
    }

    public void depositarCredito(int credito){
        this.credit += credito;
    }

    public void retirarCredito(int credito){ this.credit -= credito; }

    public void createOrder(Provider provider, Menu menu, int cantidad, LocalDateTime fechaDeEntrega){
        //todo
        // falta tipoDeEntrega
        if (fechaDeEntregaValida(fechaDeEntrega)){
            if (provider.tieneSuficientesMenus(menu, cantidad)){
                if (this.tieneCreditoParaMenu(menu, cantidad)){
                    this.retirarCredito(cantidad * menu.getPrice());
                    // agregar credito al provider?
                    Order order = new OrderBuilder().withMenu(menu).withCantidad(cantidad).withFechaEntrega(fechaDeEntrega).build();
                    this.orders.add(order);
                }
                else {
                    throw new CreditoInsuficiente("No dispones de crédito para comprar este menú");
                }
            }
            else {
                throw new VentasDeMenuExcedidas("El proveedor excedió las ventas para este menú");
            }
        }
        else {
            throw new FechaDeEntregaInvalida("La fecha de entrega es muy pronto");
        }
    }

    public boolean tieneCreditoParaMenu(Menu menu, int cantidad) {
        return this.credit >= cantidad * menu.getPrice();
    }

    private boolean fechaDeEntregaValida(LocalDateTime fechaDeEntrega){
        // Chequea que falten por lo menos 48 horas para la fecha de entrega, contemplando sólo días hábiles.
        LocalDateTime desde = LocalDateTime.now();

        long numOfDaysBetween = ChronoUnit.DAYS.between(desde, fechaDeEntrega);
        List dias =  IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> desde.plusDays(i))
                .collect(Collectors.toList());

        FeriadoChecker feriadoChecker = new FeriadoChecker(desde.getYear());
        feriadoChecker.filtarDiasHabiles(dias);

        return ChronoUnit.HOURS.between(LocalDateTime.now(), fechaDeEntrega) > 48;

    }
}

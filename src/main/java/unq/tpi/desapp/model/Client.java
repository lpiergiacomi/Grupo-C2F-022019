package unq.tpi.desapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.InsufficientCreditException;
import unq.tpi.desapp.exceptions.InvalidDeliveryDateException;
import unq.tpi.desapp.exceptions.MenuSalesExceededException;
import unq.tpi.desapp.model.holiday.HolidayChecker;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.model.menu.MenuOrder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String locality;
    private String address;
    private int credit;
    //private List<Menu> menus;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;
    private String type;

    public Client() {}

    public Client(String firstName, String lastName, String mail, String phone, String locality, String address, int credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.locality = locality;
        this.address = address;
        this.credit = credit;
        //this.menus = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void increaseCredit(int credit) {
        this.credit += credit;
    }

    public void discountCredit(int credit) {
        this.credit -= credit;
    }

    public boolean hasEnoughCredit(List<MenuOrder> menuOrders) {
        int amount = menuOrders.stream().mapToInt(m -> m.totalAmount()).sum();
        return amount <= this.getCredit();
    }

    public void paymentOrder(Provider provider, List<MenuOrder> menuOrders, LocalDateTime deliveryDate) {
        if (this.deliveryDateValid(deliveryDate)) {
            if (provider.hasEnoughMenu(menuOrders)) {
                if (this.hasEnoughCredit(menuOrders)) {
                    Order order = new Order(provider, this, deliveryDate);
                    order.sendConfirmationEmails();
                    order.decreaseClientCredit(order.totalMenus());
                    order.increaseProviderCredit(order.totalMenus());
                    orders.add(order);
                } else {
                    throw new InsufficientCreditException("No dispones de crédito para comprar este menú");
                }
            } else {
                throw new MenuSalesExceededException("El proveedor excedió las ventas para este menú");
            }
        } else {
            throw new InvalidDeliveryDateException("La fecha de entrega es muy pronto");
        }
    }

    private boolean deliveryDateValid(LocalDateTime deliveryDate) {
        // Chequea que falten por lo menos 48 horas para la fecha de entrega, contemplando sólo días hábiles.
        LocalDateTime from = LocalDateTime.now();

        long numOfDaysBetween = ChronoUnit.DAYS.between(from, deliveryDate);
        List days = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> from.plusDays(i))
                .collect(Collectors.toList());

        HolidayChecker holidayChecker = new HolidayChecker(from.getYear());
        holidayChecker.filterWorkingDays(days);

        return ChronoUnit.HOURS.between(LocalDateTime.now(), deliveryDate) > 48;

    }

}

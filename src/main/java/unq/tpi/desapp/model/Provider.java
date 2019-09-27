package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.MaxMenusException;

import javax.persistence.*;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

@Getter
@Setter
@Entity
public class Provider {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
    private String logo;
    private String locality;
    private String address;
    //private Coord coord;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private LocalTime attentionTimeBegin;
    private LocalTime attentionTimeEnd;
    private DayOfWeek attentionDayBegin;
    private DayOfWeek attentionDayEnd;
    private List<String> deliveryLocalities;
    @ElementCollection
    private List<Menu> menus;
    private int credit;

    public Provider(String name, String logo, String locality, String address, String description, String site, String mail, String phone, LocalTime attentionTimeBegin, LocalTime attentionTimeEnd, DayOfWeek attentionDayBegin, DayOfWeek attentionDayEnd, List<Menu> menus) {
        this.name = name;
        this.logo = logo;
        this.locality = locality;
        this.address = address;
        //this.coord = coord;
        this.description = description;
        this.site = site;
        this.mail = mail;
        this.phone = phone;
        this.attentionTimeBegin = attentionTimeBegin;
        this.attentionTimeEnd = attentionTimeEnd;
        this.attentionDayBegin = attentionDayBegin;
        this.attentionDayEnd = attentionDayEnd;
        this.deliveryLocalities = new ArrayList<>();
        this.menus = menus;
        this.credit = 0;
    }

    public Provider() {
    } //Hibernate pide un default constructor


    public void addMenu(Menu menu) {
        if (this.menus.size() == 20) {
            throw new MaxMenusException("No se pueden agregar más menu");
        }
        menus.add(menu);
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }

    public void increaseCredit(int credit) {
        this.credit += credit;
    }

    public void discountCredit(int credit) {
        this.credit -= credit;
    }

    public boolean hasEnoughMenu(List<MenuOrder> menuOrders) {
        return menuOrders.stream().allMatch(m -> m.getMenu().getMaxSalesPerDay() >= m.getQuantity());
    }


    /*
    public List<Menu> searchMenuName(String nameMenu) {
        List<Menu> menuResults = this.menus.stream().filter(menu -> menu.getName().equals(nameMenu)).collect(Collectors.toList());
        return menuResults;
    }
    */
}

package unq.tpi.desapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.MaxMenusException;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.model.menu.MenuOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@Table(name = "providers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String logo;
    private String locality;
    private String address;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private LocalTime attentionTimeBegin;
    private LocalTime attentionTimeEnd;
    @ElementCollection
    private List<String> daysAttention;
    @ElementCollection
    private List<String> deliveryLocalities;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menus;

    private int credit;
    private String type;

    public Provider(String name, String logo, String locality, String address, String description, String site, String mail, String phone, LocalTime attentionTimeBegin, LocalTime attentionTimeEnd, List<String> daysAttention, List<String> deliveryLocalities, List<Menu> menus, int credit) {
        this.name = name;
        this.logo = logo;
        this.locality = locality;
        this.address = address;
        this.description = description;
        this.site = site;
        this.mail = mail;
        this.phone = phone;
        this.attentionTimeBegin = attentionTimeBegin;
        this.attentionTimeEnd = attentionTimeEnd;
        this.daysAttention = daysAttention;
        this.deliveryLocalities = deliveryLocalities;
        this.menus = menus;
        this.credit = credit;
    }

    public Provider() {
    }


    public void addMenu(Menu menu) {
        if (this.menus.size() == 20) {
            throw new MaxMenusException("No se pueden agregar más menu");
        }
        menus.add(menu);
    }

    public void increaseCredit(int credit) {
        this.credit += credit;
    }

    public boolean hasEnoughMenu(List<MenuOrder> menuOrders) {
        return menuOrders.stream().allMatch(m -> m.getMenu().getMaxSalesPerDay() >= m.getQuantity());
    }
}

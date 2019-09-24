package unq.tpi.desapp.model;

import lombok.Getter;
import lombok.Setter;
import unq.tpi.desapp.exceptions.MenusMaximos;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Entity
public class Provider {

    @Id
    private String name;
    private String logo;
    private String locality;
    private String address;
    //private Coord coord;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private String attentionTime;
    private int deliveryRadius;
    @ElementCollection
    private List<Menu> menus;

    public Provider(String name, String logo, String locality, String address, String description, String site, String mail, String phone, String attentionTime, int deliveryRadius, List<Menu> menus) {
        this.name = name;
        this.logo = logo;
        this.locality = locality;
        this.address = address;
        //this.coord = coord;
        this.description = description;
        this.site = site;
        this.mail = mail;
        this.phone = phone;
        this.attentionTime = attentionTime; // Cambiar
        this.deliveryRadius = deliveryRadius; // Cambiar
        this.menus = menus;
    }

    public Provider() {
    } //Hibernate pide un default constructor


    public void addMenu(Menu menu) {
        if (this.menus.size() == 20) {
            throw new MenusMaximos("No se pueden agregar más menu");
        }
        menus.add(menu);
    }

    public boolean tieneSuficientesMenus(Menu menu, int cantidad) {
        return menu.getMaxSalesPerDay() >= cantidad;
    }

    /*
    public List<Menu> searchMenuName(String nameMenu) {
        List<Menu> menuResults = this.menus.stream().filter(menu -> menu.getName().equals(nameMenu)).collect(Collectors.toList());
        return menuResults;
    }
    */
}

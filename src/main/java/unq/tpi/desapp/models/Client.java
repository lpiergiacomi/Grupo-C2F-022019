package unq.tpi.desapp.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Client {

    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private String locality;
    private String address;
    private int credit;
    private List<Service> services;
    private List<Menu> menus;

    public Client(String firstName, String lastName, String mail, String phone, String locality, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.locality = locality;
        this.address = address;
        this.menus = new ArrayList<Menu>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> searchMenuName(String menuName) {
        //List<Menu> menuResults = new ArrayList<>();
        for (Service service : services) {
            this.menus.addAll(service.searchMenuName(menuName));
        }
        return this.menus;
    }

    public List<Menu> searchMenuLocality(String menuLocality) {
        List<Service> servicesResults = this.services.stream().filter(service -> service.getLocality().equals(menuLocality)).collect(Collectors.toList());
        //List<Menu> menuResults = new ArrayList<>();
        for (Service service : servicesResults) {
            this.menus.addAll(service.getMenus());
        }
        return this.menus;
    }

    public void sortMenusByPrice() {
        Collections.sort(menus, Comparator.comparingInt(Menu::getPrice));
    }
}

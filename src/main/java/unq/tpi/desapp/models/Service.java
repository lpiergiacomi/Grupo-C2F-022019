package unq.tpi.desapp.models;

import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private String name;
    private String locality;
    private String address;
    //private Coord coord;
    private String description;
    private String site;
    private String mail;
    private String phone;
    private String attentionTime;
    private int deliveryRadius;
    private List<Menu> menus;

    public Service(String name, String locality, String address, String description, String mail, String phone, String attentionTime) {
        this.name = name;
        this.locality = locality;
        this.address = address;
        //this.coord = coord;
        this.description = description;
        this.mail = mail;
        this.phone = phone;
        this.attentionTime = attentionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /*
    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
    */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public String getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(String attentionTime) {
        this.attentionTime = attentionTime;
    }

    public int getDeliveryRadius() {
        return deliveryRadius;
    }

    public void setDeliveryRadius(int deliveryRadius) {
        this.deliveryRadius = deliveryRadius;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> searchMenu(String nameMenu) {
        List<Menu> menuResult = this.menus.stream().filter(menu -> menu.getName().equals(nameMenu)).collect(Collectors.toList());
        return menuResult;
    }
}

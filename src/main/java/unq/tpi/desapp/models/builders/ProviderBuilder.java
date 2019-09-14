package unq.tpi.desapp.models.builders;

import unq.tpi.desapp.models.Menu;
import unq.tpi.desapp.models.Provider;

import java.util.List;

public class ProviderBuilder {

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
    private List<Menu> menus;


    public ProviderBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ProviderBuilder withLogo(String logo){
        this.logo = logo;
        return this;
    }

    public ProviderBuilder withLocality(String locality){
        this.locality = locality;
        return this;
    }

    public ProviderBuilder withAddress(String address){
        this.address = address;
        return this;
    }

    public ProviderBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public ProviderBuilder withSite(String site){
        this.site = site;
        return this;
    }

    public ProviderBuilder withMail(String mail){
        this.mail = mail;
        return this;
    }

    public ProviderBuilder withPhone(String phone){
        this.phone = phone;
        return this;
    }

    public ProviderBuilder withAttentionTime(String attentionTime){
        this.attentionTime = attentionTime;
        return this;
    }

    public ProviderBuilder withDeliveryRadius(int deliveryRadius){
        this.deliveryRadius = deliveryRadius;
        return this;
    }

    public ProviderBuilder withMenus(List<Menu> menus){
        this.menus = menus;
        return this;
    }



    public Provider build() {
        return new Provider(name, logo, locality, address, description, site, mail, phone, attentionTime, deliveryRadius, menus);
    }
}

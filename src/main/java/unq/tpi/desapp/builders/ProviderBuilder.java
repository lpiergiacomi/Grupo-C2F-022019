package unq.tpi.desapp.builders;

import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.model.Provider;

import java.time.LocalTime;
import java.util.List;

public class ProviderBuilder {

    private String name;
    private String logo;
    private String locality;
    private String address;
    private String description;
    private String site;
    private String mail;
    private String password;
    private String phone;
    private LocalTime attentionTimeBegin;
    private LocalTime attentionTimeEnd;
    private List<String> daysAttention;
    private List<String> deliveryLocalities;
    private List<Menu> menus;
    private int credit;


    public ProviderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProviderBuilder withLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public ProviderBuilder withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public ProviderBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ProviderBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProviderBuilder withSite(String site) {
        this.site = site;
        return this;
    }

    public ProviderBuilder withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ProviderBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ProviderBuilder withAttentionTimeBegin(LocalTime attentionTimeBegin) {
        this.attentionTimeBegin = attentionTimeBegin;
        return this;
    }

    public ProviderBuilder withAttentionTimeEnd(LocalTime attentionTimeEnd) {
        this.attentionTimeEnd = attentionTimeEnd;
        return this;
    }

    public ProviderBuilder withDaysAttention(List<String> daysAttention) {
        this.daysAttention = daysAttention;
        return this;
    }

    public ProviderBuilder withDeliveryLocalities(List<String> deliveryLocalities) {
        this.deliveryLocalities = deliveryLocalities;
        return this;
    }

    public ProviderBuilder withMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public ProviderBuilder withCredit(int credit) {
        this.credit = credit;
        return this;
    }
    public ProviderBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Provider build() {
        return new Provider(name, logo, locality, address, description, site, mail, password, phone, attentionTimeBegin, attentionTimeEnd, daysAttention, deliveryLocalities, menus, credit);
    }
}

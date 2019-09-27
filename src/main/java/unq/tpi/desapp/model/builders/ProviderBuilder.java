package unq.tpi.desapp.model.builders;

import unq.tpi.desapp.model.Coord;
import unq.tpi.desapp.model.Menu;
import unq.tpi.desapp.model.Provider;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
    private LocalTime attentionTimeBegin;
    private LocalTime attentionTimeEnd;
    private DayOfWeek attentionDayBegin;
    private DayOfWeek attentionDayEnd;
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

    public ProviderBuilder withAttentionDayBegin(DayOfWeek attentionDayBegin) {
        this.attentionDayBegin = attentionDayBegin;
        return this;
    }

    public ProviderBuilder withAttentionDayEnd(DayOfWeek attentionDayEnd) {
        this.attentionDayEnd = attentionDayEnd;
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


    public Provider build() {
        return new Provider(name, logo, locality, address, description, site, mail, phone, attentionTimeBegin, attentionTimeEnd, attentionDayBegin, attentionDayEnd, menus);
    }
}

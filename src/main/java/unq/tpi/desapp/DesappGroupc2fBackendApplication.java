package unq.tpi.desapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.builders.MenuBuilder;
import unq.tpi.desapp.builders.ProviderBuilder;
import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.menu.MenuCategory;
import unq.tpi.desapp.persistence.MenuRepository;
import unq.tpi.desapp.persistence.ProviderRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DesappGroupc2fBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(DesappGroupc2fBackendApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ProviderRepository providerRepository, MenuRepository menuRepository) {
        return args -> {

            List<Menu> menus1 = new ArrayList<>();
            List<MenuCategory> categories = new ArrayList<>();
            categories.add(MenuCategory.Pizza);
            Menu menu1 = new MenuBuilder().withName("Cuarto de libra con queso").withPrice(200).withDescription("Carne 100% vacuna, queso.").withQuantityPrice(150).withQuantityPrice2(100).withDeliveryTimeAverage(1800).withPreparationTime(2700).withDeliveryPrice(30).withValidityDateBegin(new Date()).withValidityDateEnd(new Date()).withMinQuantity(20).withMinQuantity2(50).withCategories(categories).withMaxSalesPerDay(20).build();
            Menu menu2 = new MenuBuilder().withName("Triple Mac").withPrice(150).withDescription("Dos hamburguesas de carne 100% vacuna.").withQuantityPrice(100).withQuantityPrice2(75).withDeliveryTimeAverage(2400).withPreparationTime(1200).withDeliveryPrice(30).withValidityDateBegin(new Date()).withValidityDateEnd(new Date()).withMinQuantity(20).withMinQuantity2(50).withCategories(categories).withMaxSalesPerDay(20).build();
            menus1.add(menu1);
            menus1.add(menu2);

            List<Menu> menus2 = new ArrayList<>();
            Menu menu3 = new MenuBuilder().withName("Doble BK Stacker").withPrice(500).withDescription("Carne a la parrilla, queso cheddar").withQuantityPrice(375).withQuantityPrice2(210).withDeliveryTimeAverage(900).withPreparationTime(3600).withDeliveryPrice(30).withValidityDateBegin(new Date()).withValidityDateEnd(new Date()).withMinQuantity(20).withMinQuantity2(50).withCategories(categories).withMaxSalesPerDay(20).build();
            Menu menu4 = new MenuBuilder().withName("Doble Cuarto XL").withPrice(1000).withDescription("Dos carnes a la parrilla, quesos cheddar").withQuantityPrice(650).withQuantityPrice2(320).withDeliveryTimeAverage(3600).withPreparationTime(600).withDeliveryPrice(30).withValidityDateBegin(new Date()).withValidityDateEnd(new Date()).withMinQuantity(20).withMinQuantity2(50).withCategories(categories).withMaxSalesPerDay(20).build();
            menus2.add(menu3);
            menus2.add(menu4);


            List<String> daysAttention = new ArrayList<>();
            daysAttention.add("Lunes");
            daysAttention.add("Miercoles");
            daysAttention.add("Viernes");

            Provider provider1 = new ProviderBuilder().withName("Mc Donalds").withLogo("logo_mc.jpeg").withLocality("Quilmes").withAddress("Rivadavia 150, Quilmes, Buenos Aires, Argentina").withDescription("Típica casa de hamburguesas Mc Donalds en Quilmes").withSite("www.mcdonalds.com.ar/quilmes").withMail("esteban.matas@xappia.com").withPassword("estebanesteban.matas@xappia.comViandasYa").withPhone("+541234567890").withAttentionTimeBegin(LocalTime.now()).withAttentionTimeEnd(LocalTime.now()).withDaysAttention(daysAttention).withMenus(menus1).build();
            Provider provider2 = new ProviderBuilder().withName("Burger King").withLogo("logo_burger.png").withLocality("Bernal").withAddress("9 de Julio 150, Bernal, Buenos Aires, Argentina").withDescription("Típica casa de hamburguesas Burguer King en Bernal").withSite("www.bk.com.ar/bernal").withMail("pier_cai@hotmail.com").withPassword("bkbernalpier_cai@hotmail.comViandasYa").withPhone("+541231231231").withAttentionTimeBegin(LocalTime.now()).withAttentionTimeEnd(LocalTime.now()).withDaysAttention(daysAttention).withMenus(menus2).build();
            menu1.setProvider(provider1);
            menu2.setProvider(provider1);
            menu3.setProvider(provider2);
            menu4.setProvider(provider2);

            menuRepository.save(menu1);
            menuRepository.save(menu2);
            menuRepository.save(menu3);
            menuRepository.save(menu4);

            providerRepository.save(provider1);
            providerRepository.save(provider2);

        };
    }
}

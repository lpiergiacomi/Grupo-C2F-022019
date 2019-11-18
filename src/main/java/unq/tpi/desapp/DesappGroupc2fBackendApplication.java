package unq.tpi.desapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.persistence.MenuRepository;
import unq.tpi.desapp.persistence.ProviderRepository;

import java.util.ArrayList;
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
            Menu menu1 = new MenuBuilder().withName("Menu 1").withPrice(200).withQuantityPrice(150).withQuantityPrice2(100).withDeliveryTimeAverage(1800).withPreparationTime(2700).build();
            Menu menu2 = new MenuBuilder().withName("Menu 2").withPrice(150).withQuantityPrice(100).withQuantityPrice2(75).withDeliveryTimeAverage(2400).withPreparationTime(1200).build();
            menus1.add(menu1);
            menus1.add(menu2);

            List<Menu> menus2 = new ArrayList<>();
            Menu menu3 = new MenuBuilder().withName("Menu 3").withPrice(500).withQuantityPrice(375).withQuantityPrice2(210).withDeliveryTimeAverage(900).withPreparationTime(3600).build();
            Menu menu4 = new MenuBuilder().withName("Menu 4").withPrice(1000).withQuantityPrice(650).withQuantityPrice2(320).withDeliveryTimeAverage(3600).withPreparationTime(600).build();
            menus2.add(menu3);
            menus2.add(menu4);

            Provider provider1 = new ProviderBuilder().withName("Proveedor 1").withMenus(menus1).build();
            Provider provider2 = new ProviderBuilder().withName("Proveedor 2").withMenus(menus2).build();

            menuRepository.save(menu1);
            menuRepository.save(menu2);
            menuRepository.save(menu3);
            menuRepository.save(menu4);

            providerRepository.save(provider1);
            providerRepository.save(provider2);
        };
    }
}

package unq.tpi.desapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unq.tpi.desapp.model.builders.MenuBuilder;
import unq.tpi.desapp.model.builders.ProviderBuilder;
import unq.tpi.desapp.persistence.MenuRepository;
import unq.tpi.desapp.persistence.ProviderRepository;

@SpringBootApplication
public class DesappGroupc2fBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(DesappGroupc2fBackendApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ProviderRepository providerRepository, MenuRepository menuRepository) {
        return args -> {
            providerRepository.save(new ProviderBuilder().withName("Proveedor 1").build());
            providerRepository.save(new ProviderBuilder().withName("Proveedor 2").build());
            menuRepository.save(new MenuBuilder().withName("Menu 1").build());
            menuRepository.save(new MenuBuilder().withName("Menu 2").build());
        };
    }
}

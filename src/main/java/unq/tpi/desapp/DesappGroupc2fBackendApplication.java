package unq.tpi.desapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unq.tpi.desapp.model.builders.ProviderBuilder;
import unq.tpi.desapp.persistence.ProviderRepository;

@SpringBootApplication
public class DesappGroupc2fBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(DesappGroupc2fBackendApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ProviderRepository providerRepository){
        return args -> {
            providerRepository.save(new ProviderBuilder().withName("Proveedor 1").build());
            providerRepository.save(new ProviderBuilder().withName("Proveedor 2").build());
        };
    }
}

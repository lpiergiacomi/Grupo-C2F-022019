package unq.tpi.desapp.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.persistence.ProviderRepository;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class HomeController {

    private final ProviderRepository providerRepository;

    HomeController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }


    @GetMapping(value = "/")
    public Iterable<Provider> index() {
        return providerRepository.findAll();
    }
}

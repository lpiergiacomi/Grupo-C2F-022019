package unq.tpi.desapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.builders.ProviderBuilder;
import unq.tpi.desapp.persistence.IProvider;


@RestController
public class HomeController {

    @Autowired
    private IProvider provider;


    @GetMapping("/")
    public String index() {
        Provider viri = new ProviderBuilder().withName("Viri Burguer").build();
        provider.save(viri);
        return provider.getOne("Viri Burguer").getName();
    }
}

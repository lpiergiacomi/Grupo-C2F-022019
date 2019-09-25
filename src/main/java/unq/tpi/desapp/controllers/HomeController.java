package unq.tpi.desapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.builders.ProviderBuilder;
import unq.tpi.desapp.persistence.IProvider;

import java.util.List;


@RestController
public class HomeController {

    @Autowired
    private IProvider provider;


    @RequestMapping("/")
    public String index() {
        //Provider viri = new ProviderBuilder().withName("Viri Burguer").build();
        //Provider antares = new ProviderBuilder().withName("Antares").build();
        //provider.save(viri);
        //provider.save(antares);
        return "Hola mundo";
    }
}

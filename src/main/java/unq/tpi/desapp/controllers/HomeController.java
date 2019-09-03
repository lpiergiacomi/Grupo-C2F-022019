package unq.tpi.desapp.controllers;


import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController {


    @RequestMapping("/")
    public String index() {
        return "Hola mundo";
    }
}

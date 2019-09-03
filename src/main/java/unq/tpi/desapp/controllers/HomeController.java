package unq.tpi.desapp.controllers;


import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/")
public class HomeController {


    //@CrossOrigin(origins = "https://app-grupoc2f-022019.herokuapp.com")
    @GetMapping("/")
    public String index() {
        return "Hola mundo";
    }
}

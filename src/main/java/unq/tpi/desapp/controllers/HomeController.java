package unq.tpi.desapp.controllers;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class HomeController {


    @CrossOrigin(origins = "https://app-grupoc2f-022019.herokuapp.com")
    @GetMapping("/home")
    public String index() {
        return "Hola mundo";
    }
}

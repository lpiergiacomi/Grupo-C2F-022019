package unq.tpi.desapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@SpringBootApplication
public class DesappGroupc2fBackendApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DesappGroupc2fBackendApplication.class, args);
    }

}

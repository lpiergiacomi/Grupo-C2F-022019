package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import unq.tpi.desapp.exceptions.ElementNotFoundException;
import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.persistence.ClientRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/clients")
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/find/{mail}")
    public ResponseEntity<Client> getProviderByMail(@PathVariable(value = "mail") String mail){
        Client client = null;
        try {
            client = clientRepository.findByMail(mail).get();
            return ResponseEntity.ok().body(client);
        } catch(NoSuchElementException e) {
        return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @RequestBody porque el menu viene en formato JSON
    @PostMapping("/clients")
    @ResponseBody
    // @Valid para que valide las condiciones configuradas en Menu (@NotEmpty, @Size, @Email)
    // Hay que inyectarle BindingResult que es lo que contiene todos los mensajes de error, para saber si ocurrió algún problema
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult result) {

        Client newClient = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newClient = this.clientRepository.save(client);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NoSuchFieldError e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", "No existe un cliente con ese id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con éxito");
        response.put("client", newClient);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
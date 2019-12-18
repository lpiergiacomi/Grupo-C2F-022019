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
import unq.tpi.desapp.exceptions.InsufficientCreditException;
import unq.tpi.desapp.exceptions.InvalidDeliveryDateException;
import unq.tpi.desapp.exceptions.MenuSalesExceededException;
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.model.Client;
import unq.tpi.desapp.persistence.ClientRepository;
import unq.tpi.desapp.persistence.MenuOrderRepository;
import unq.tpi.desapp.persistence.ProviderRepository;
import org.apache.log4j.Logger;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;
    private ProviderRepository providerRepository;
    private MenuOrderRepository menuOrderRepository;
    public static Logger log = Logger.getLogger(ClientController.class);




    ClientController(ClientRepository clientRepository, ProviderRepository providerRepository, MenuOrderRepository menuOrderRepository) {

        this.clientRepository = clientRepository;
        this.providerRepository = providerRepository;
        this.menuOrderRepository = menuOrderRepository;
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
    public ResponseEntity<?> getClientByMail(@PathVariable(value = "mail") String mail){
        Map<String, Object> response = new HashMap<>();

        Client client = null;
        try {
            client = clientRepository.findByMail(mail).get();
            response.put("mensaje", "ok");
            response.put("client", client);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            response.put("mensaje", "error");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClientById(@PathVariable(value = "id") Long id){
        Map<String, Object> response = new HashMap<>();

        Client client = null;
        try {
            client = clientRepository.findById(id).get();
            response.put("mensaje", "ok");
            response.put("client", client);
            log.info("Se encontro correctamente al usuario con id " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            response.put("mensaje", "No existe un cliente con el id " + id);
            log.error("Error al buscar el cliente con id " + id,e);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/clients")
    @ResponseBody
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


    @PostMapping("/clients/purchase")
    @ResponseBody
    public ResponseEntity<?> createOrder(@Valid @RequestBody MenuOrder menuOrder, BindingResult result) {

        MenuOrder newMenuOrder = null;
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
            Client client = this.clientRepository.findById(menuOrder.getIdClient()).get();
            if (!client.hasPendingRates()){
                client.paymentOrder(menuOrder);
                this.clientRepository.save(client);
            }
            else {
                response.put("mensaje", "Debes calificar todas tus compras");
                response.put("error", "Debes calificar todas tus compras");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //newMenuOrder = this.menuOrderRepository.save(menuOrder);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (InsufficientCreditException e){
            response.put("mensaje", "No dispones de crédito para comprar este menú");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (MenuSalesExceededException e){
            response.put("mensaje", "El proveedor excedió las ventas para este menú");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (InvalidDeliveryDateException e){
            response.put("mensaje", "La fecha de entrega es muy pronto");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NoSuchFieldError e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", "No existe un cliente con ese id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con éxito");
        response.put("menuOrder", newMenuOrder);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
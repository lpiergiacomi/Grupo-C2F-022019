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
import unq.tpi.desapp.menu.MenuOrder;
import unq.tpi.desapp.persistence.MenuOrderRepository;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class MenuOrderController {

    @Autowired
    private MenuOrderRepository menuOrderRepository;


    MenuOrderController(MenuOrderRepository menuOrderRepository) {
        this.menuOrderRepository = menuOrderRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/menuorder")
    public Iterable<MenuOrder> getAllMenuOrders() {
        return menuOrderRepository.findAll();
    }


    @GetMapping("/menuorder/{id}")
    public ResponseEntity<MenuOrder> getMenuOrderById(@PathVariable(value = "id") Long id)
            throws ElementNotFoundException {
        MenuOrder menuOrder = menuOrderRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("La orden no pudo ser encontrada para el id: " + id));
        return ResponseEntity.ok().body(menuOrder);
    }


    @GetMapping("/menuorder/client/{idClient}")
    public ResponseEntity<List<MenuOrder>> getMenuOrderByIdClient(@PathVariable(value = "idClient") Long idClient){
        List<MenuOrder> menuOrders = null;
        try {
            menuOrders = menuOrderRepository.findByIdClient(idClient).get();
            return ResponseEntity.ok().body(menuOrders);
        } catch(NoSuchElementException e) {
            return ResponseEntity.ok().body(new ArrayList<>());
        }
    }

    @GetMapping("/menuorder/provider/{idProvider}")
    public ResponseEntity<List<MenuOrder>> getMenuOrderByIdProvider(@PathVariable(value = "idProvider") Long idProvider){
        List<MenuOrder> menuOrders = null;
        try {
            menuOrders = menuOrderRepository.findByIdProvider(idProvider).get();
            return ResponseEntity.ok().body(menuOrders);
        } catch(NoSuchElementException e) {
            return ResponseEntity.ok().body(new ArrayList<>());
        }
    }

    @PutMapping("/menuorder/{id}")
    public ResponseEntity<?> updateMenuOrder(@Valid @RequestBody MenuOrder menuOrderDetails, BindingResult result, @PathVariable Long id) {
        MenuOrder menuOrder = menuOrderRepository.findById(id).get();
        MenuOrder menuOrderUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (menuOrder == null) {
            response.put("mensaje", "Error! No se pudo editar la orden con ID: " + id + " ya que no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            menuOrder.setMenu(menuOrderDetails.getMenu());
            menuOrder.setQuantity(menuOrderDetails.getQuantity());
            menuOrder.setPrice(menuOrderDetails.getPrice());
            menuOrder.setDeliveryType(menuOrderDetails.getDeliveryType());
            menuOrder.setDeliveryDate(menuOrderDetails.getDeliveryDate());
            menuOrder.setDeliveryTime(menuOrderDetails.getDeliveryTime());
            menuOrder.setIdClient(menuOrderDetails.getIdClient());
            menuOrder.setQualification(menuOrderDetails.getQualification());

            menuOrderUpdated = this.menuOrderRepository.save(menuOrder);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la orden en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La orden ha sido actualizada con éxito");
        response.put("menuOrder", menuOrderUpdated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/menuorder/rate/{id}")
    public ResponseEntity<?> rateMenuOrder(@Valid @RequestBody MenuOrder menuOrderDetails, BindingResult result, @PathVariable Long id) {
        MenuOrder menuOrder = menuOrderRepository.findById(id).get();
        MenuOrder menuOrderUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (menuOrder == null) {
            response.put("mensaje", "Error! No se pudo editar la orden con ID: " + id + " ya que no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            menuOrder.setQualification(menuOrderDetails.getQualification());
            menuOrderUpdated = this.menuOrderRepository.save(menuOrder);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la orden en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La orden ha sido calificada con " + menuOrderDetails.getQualification());
        response.put("menuOrder", menuOrderUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

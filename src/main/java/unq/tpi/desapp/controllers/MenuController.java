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
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.persistence.MenuRepository;
import unq.tpi.desapp.persistence.ProviderRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProviderRepository providerRepository;

    MenuController(MenuRepository menuRepository, ProviderRepository providerRepository) {
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/menus")
    public Iterable<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable(value = "id") Long id)
            throws ElementNotFoundException {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El menu no pudo ser encontrado para el id: " + id));
        return ResponseEntity.ok().body(menu);
    }
/*
    @PostMapping("/menus")
    public Menu createMenu(@Valid @RequestBody Menu menu) {
        return menuRepository.save(menu);
    }
*/

    // @RequestBody porque el menu viene en formato JSON
    @PostMapping("/menus")
    @ResponseBody
    // @Valid para que valide las condiciones configuradas en Menu (@NotEmpty, @Size, @Email)
    // Hay que inyectarle BindingResult que es lo que contiene todos los mensajes de error, para saber si ocurrió algún problema
    public ResponseEntity<?> createMenu(@Valid @RequestBody Menu menu, BindingResult result) {

        Menu menuNuevo = null;
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
            Provider provider = providerRepository.findById(menu.getProvider().getId()).get();
            menu.setProvider(provider);
            menuNuevo = this.menuRepository.save(menu);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NoSuchFieldError e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", "No existe un proveedor con ese id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // En caso que se cree correctamente, devolvemos el menu y un mensaje
        //menuNuevo.setCreateAt(new Date());
        response.put("mensaje", "El menu ha sido creado con éxito");
        response.put("menu", menuNuevo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody Menu menuDetails) throws ElementNotFoundException {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El menu no pudo ser encontrado para el id: " + id));

        menu.setName(menuDetails.getName());
        menu.setPrice(menuDetails.getPrice());
        menu.setMinQuantity(menuDetails.getMinQuantity());
        menu.setMinQuantity2(menuDetails.getMinQuantity2());
        final Menu updatedMenu = menuRepository.save(menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/menus/{id}")
    public Map<String, Boolean> deleteMenu(@PathVariable(value = "id") Long id)
            throws ElementNotFoundException {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El menu no pudo ser encontrado para el id : " + id));

        menuRepository.delete(menu);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

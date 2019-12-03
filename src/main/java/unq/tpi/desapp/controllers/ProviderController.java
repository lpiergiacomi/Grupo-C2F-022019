package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import unq.tpi.desapp.exceptions.ElementNotFoundException;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.persistence.ProviderRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @RequestMapping("/providers")
    public Iterable<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable(value = "id") Long id)

            throws ElementNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));
        return ResponseEntity.ok().body(provider);
    }

    @GetMapping("/providers/find/{mail}")
    public ResponseEntity<Integer> getProviderByMail(@PathVariable(value = "mail") String mail)
            throws ElementNotFoundException {
        Integer id = providerRepository.findByMail(mail)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el mail: " + mail));
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("/providers")
    public ResponseEntity<?> createProvider(@Valid @RequestBody Provider provider, BindingResult result) {

        Provider providerNuevo = null;
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
            providerNuevo = this.providerRepository.save(provider);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // En caso que se cree correctamente, devolvemos el menu y un mensaje
        //menuNuevo.setCreateAt(new Date());
        response.put("mensaje", "El proveedor ha sido creado con éxito");
        response.put("provider", providerNuevo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/providers/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody Provider providerDetails) throws ElementNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));
        provider.setName(providerDetails.getName());
        provider.setLocality(providerDetails.getLocality());
        provider.setAddress(providerDetails.getAddress());
        final Provider updatedProvider = providerRepository.save(provider);
        return ResponseEntity.ok(updatedProvider);
    }

    @DeleteMapping("/providers/{id}")
    public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long id)
            throws ElementNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el id : " + id));

        providerRepository.delete(provider);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/providers/{id}/credit")
    public ResponseEntity<Provider> updateCredit(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Provider providerCredit) throws ElementNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));
      
        provider.setCredit(providerCredit.getCredit());
        final Provider updatedCredit = providerRepository.save(provider);
        return ResponseEntity.ok(updatedCredit);
    }

    @GetMapping("/providers/{id}/menus")
    public ResponseEntity<List<Menu>> getProviderMenus(@PathVariable(value = "id") Long id)

            throws ElementNotFoundException {
        List<Menu> menus = providerRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("El proveedor no pudo ser encontrado para el id: " + id)).getMenus();
        return ResponseEntity.ok().body(menus);
    }
}

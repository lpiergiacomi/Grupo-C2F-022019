package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unq.tpi.desapp.exceptions.ElementNotFoundException;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.persistence.ProviderRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PostMapping("/providers")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
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

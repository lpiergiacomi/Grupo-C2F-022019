package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import unq.tpi.desapp.exceptions.ProviderNotFoundException;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.persistence.ProviderRepository;

import javax.validation.Valid;
import java.util.HashMap;
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
            throws ProviderNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));
        return ResponseEntity.ok().body(provider);
    }

    @PostMapping("/providers")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
    }

    @PutMapping("/providers/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody Provider providerDetails) throws ProviderNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));

        provider.setName(providerDetails.getName());
        provider.setLocality(providerDetails.getLocality());
        provider.setAddress(providerDetails.getAddress());
        final Provider updatedProvider = providerRepository.save(provider);
        return ResponseEntity.ok(updatedProvider);
    }

    @DeleteMapping("/providers/{id}")
    public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long id)
            throws ProviderNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("El proveedor no pudo ser encontrado para el id : " + id));

        providerRepository.delete(provider);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/providers/{id}/credit")
    public ResponseEntity<Provider> updateCredit(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Provider providerCredit) throws ProviderNotFoundException {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("El proveedor no pudo ser encontrado para el id: " + id));

        provider.setCredit(providerCredit.getCredit());
        final Provider updatedCredit = providerRepository.save(provider);
        return ResponseEntity.ok(updatedCredit);
    }
}

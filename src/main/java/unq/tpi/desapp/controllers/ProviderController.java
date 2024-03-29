package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unq.tpi.desapp.exceptions.ElementNotFoundException;
import unq.tpi.desapp.exceptions.InsufficientCreditException;
import unq.tpi.desapp.model.Provider;
import unq.tpi.desapp.menu.Menu;
import unq.tpi.desapp.persistence.ProviderRepository;
import unq.tpi.desapp.services.IUploadFileService;


import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private IUploadFileService uploadFileService;

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

    @PostMapping("/providers/upload")
    public ResponseEntity<?> upload(@RequestParam("logo") MultipartFile logo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();
        Provider provider = null;
        try {
            provider = providerRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            response.put("mensaje", "No se encuentra un proveedor con id " + id);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!logo.isEmpty()) {

            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFileService.copiar(logo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Si ya tiene una foto cargada que la elimine
            String nombreFotoAnterior = provider.getLogo();
            uploadFileService.eliminar(nombreFotoAnterior);

            provider.setLogo(nombreArchivo);

            providerRepository.save(provider);

            response.put("provider", provider);
            response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
        }


        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/providers/uploads/img/{nombreLogo:.+}")
    //:.+ es la expresión regular para indicar que la foto va a tener un "." y después la extensión
    public ResponseEntity<Resource> verLogo(@PathVariable String nombreLogo) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.cargar(nombreLogo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + recurso.getFilename());

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

    }

    @GetMapping("/providers/find/{mail}")
    public ResponseEntity<?> getProviderByMail(@PathVariable(value = "mail") String mail){
        Map<String, Object> response = new HashMap<>();

        Provider provider = null;
        try {
            provider = providerRepository.findByMail(mail).get();
            response.put("mensaje", "ok");
            response.put("provider", provider);
            return ResponseEntity.ok().body(response);
        } catch(NoSuchElementException e) {
            response.put("mensaje", "error");
            response.put("error", "No se encuentra un proveedor con mail " + mail);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/providers/withdraw/{id}")
    public ResponseEntity<?> withdrawCredit(@Valid @RequestBody Provider providerDetails, BindingResult result, @PathVariable Long id) {
        Provider provider = providerRepository.findById(id).get();
        Provider providerUpdated= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (provider == null) {
            response.put("mensaje", "error");
            response.put("error", "Error! No se pudo editar el proveedor con ID: " + id + " ya que no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            provider.decreaseCredit(providerDetails.getCredit());
            providerUpdated = this.providerRepository.save(provider);

        } catch (DataAccessException e) {
            response.put("mensaje", "error");
            response.put("error", "Error al actualizar el cliente en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InsufficientCreditException e) {
            response.put("mensaje", "error");
            response.put("error", "No tenés ese dinero para retirar");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("mensaje", "Retiraste $" + providerDetails.getCredit() + " y ahora tenés $" + providerUpdated.getCredit());
        response.put("provider", providerUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/providers/updatecredit/{id}")
    public ResponseEntity<?> updateProvider(@Valid @RequestBody Provider providerDetails, BindingResult result, @PathVariable Long id) {
        Provider provider = providerRepository.findById(id).get();
        Provider providerUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (provider == null) {
            response.put("mensaje", "Error! No se pudo editar el proveedor con ID: " + id + " ya que no existe en la base de datos");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            provider.setCredit(providerDetails.getCredit());
            providerUpdated = this.providerRepository.save(provider);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el proveedor en la base de datos");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("provider", providerUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}



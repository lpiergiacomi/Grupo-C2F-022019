package unq.tpi.desapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unq.tpi.desapp.exceptions.ElementNotFoundException;
import unq.tpi.desapp.model.menu.Menu;
import unq.tpi.desapp.persistence.MenuRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = {"*"})
@Controller
@ResponseBody
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
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

    @PostMapping("/menus")
    public Menu createMenu(@Valid @RequestBody Menu menu) {
        return menuRepository.save(menu);
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

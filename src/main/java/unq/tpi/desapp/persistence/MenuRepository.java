package unq.tpi.desapp.persistence;

import org.springframework.data.repository.CrudRepository;
import unq.tpi.desapp.model.menu.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {
}

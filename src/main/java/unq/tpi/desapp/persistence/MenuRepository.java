package unq.tpi.desapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unq.tpi.desapp.menu.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}

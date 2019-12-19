package unq.tpi.desapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unq.tpi.desapp.menu.MenuOrder;

import java.util.List;
import java.util.Optional;

public interface MenuOrderRepository extends JpaRepository<MenuOrder, Long> {

    @Query("SELECT mo FROM MenuOrder mo WHERE mo.idClient = :idClient")
    Optional<List<MenuOrder>> findByIdClient(Long idClient);

    @Query("SELECT mo FROM MenuOrder mo WHERE mo.menu.provider.id = :idProvider")
    Optional<List<MenuOrder>> findByIdProvider(Long idProvider);
}

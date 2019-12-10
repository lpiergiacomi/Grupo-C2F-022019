package unq.tpi.desapp.persistence;

import org.springframework.data.jpa.repository.Query;
import unq.tpi.desapp.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.mail = :email")
    Optional<Client> findByMail(String email);
}

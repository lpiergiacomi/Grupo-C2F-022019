package unq.tpi.desapp.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unq.tpi.desapp.model.Provider;

import java.util.Optional;

public interface ProviderRepository extends CrudRepository<Provider, Long> {

    @Query("SELECT id FROM Provider WHERE mail = :email")
    public Optional<Integer> findByMail(String email);
}

package unq.tpi.desapp.persistence;

import org.springframework.data.repository.CrudRepository;
import unq.tpi.desapp.model.Provider;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
}

package unq.tpi.desapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import unq.tpi.desapp.model.Provider;

public interface IProvider extends JpaRepository<Provider, String> {
}

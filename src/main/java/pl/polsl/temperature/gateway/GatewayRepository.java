package pl.polsl.temperature.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

}

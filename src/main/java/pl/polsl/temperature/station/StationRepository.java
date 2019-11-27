package pl.polsl.temperature.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StationRepository extends JpaRepository<Station, Long> {

}

package pl.polsl.temperature.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}

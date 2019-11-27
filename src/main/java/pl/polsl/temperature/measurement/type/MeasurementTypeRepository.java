package pl.polsl.temperature.measurement.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.polsl.temperature.user.User;

import java.util.List;

@RepositoryRestResource
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Long> {

    List<MeasurementType> findAllByOwnerUser(User ownerUser);

}
package nl.topicus.enginetrouble.core;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.topicus.enginetrouble.core.model.Car;

@Repository
public interface EngineTroubleRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByReference(String reference);
}

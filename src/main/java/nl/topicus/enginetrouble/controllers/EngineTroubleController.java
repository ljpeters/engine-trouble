package nl.topicus.enginetrouble.controllers;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.topicus.enginetrouble.core.EngineTroubleRepository;
import nl.topicus.enginetrouble.core.model.Car;
import nl.topicus.enginetrouble.core.model.Engine;
import nl.topicus.enginetrouble.core.model.Piston;

@RestController
public class EngineTroubleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EngineTroubleController.class);

    private final EngineTroubleRepository engineTroubleRepository;

    public EngineTroubleController(EngineTroubleRepository engineTroubleRepository) {
        this.engineTroubleRepository = engineTroubleRepository;
    }

    @PostMapping("/engine-trouble")
    public ResponseEntity<String> engineTroublePost() {
        final var carWithoutEngine = new Car(null, UUID.randomUUID().toString(), null);
        engineTroubleRepository.save(carWithoutEngine);

        final var car = new Car(
                null,
                UUID.randomUUID().toString(),
                new Engine(
                        null,
                        Set.of(
                                new Piston(
                                        null,
                                        "piston1"
                                ),
                                new Piston(
                                        null,
                                        "piston2"
                                ),
                                new Piston(
                                        null,
                                        "piston3"
                                )
                        )
                ));
        final var engine = car.engine();
        LOGGER.info("Number of pistons in engine: {}", engine.pistons().size());

        engineTroubleRepository.save(car);

        final var carFound = engineTroubleRepository.findByReference(car.reference()).orElseThrow(() -> new RuntimeException("No car with id [" + car.reference() + "] could be found."));
        final var engineWithoutPistons = carFound.engine();
        if (engineWithoutPistons.pistons().isEmpty()) {
            LOGGER.error("PISTONS WERE STOLEN FROM ENGINE");
            return ResponseEntity.internalServerError().build();
        }
        LOGGER.info("Number of pistons in engine: {}", engineWithoutPistons.pistons().size());
        return ResponseEntity.ok(carFound.reference());
    }
}

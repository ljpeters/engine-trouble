package nl.topicus.enginetrouble.core.model;

import org.springframework.data.annotation.Id;

public record Piston(@Id Long id,
                     String name) {
}

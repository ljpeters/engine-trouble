package nl.topicus.enginetrouble.core.model;

import java.util.Set;

import org.springframework.data.annotation.Id;

public record Engine(@Id Long id,
                     Set<Piston> pistons) {
}

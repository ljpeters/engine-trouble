package nl.topicus.enginetrouble.core.model;

import org.springframework.data.annotation.Id;

public record Car(@Id Long id,
                  String reference,
                  Engine engine) {
}

package nl.topicus.enginetrouble;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class EngineTroubleIT {

    @Test
    public void callEngineTrouble() {
        final var response = given()
                .baseUri(System.getProperty("it.pecan_uri", "http://localhost:8080"))
                .basePath("/engine-trouble")
                .post();
        if (response.statusCode() != HttpStatus.OK.value()) {
            throw new RuntimeException("Service responded with status code " + response.statusCode());
        }
    }

}

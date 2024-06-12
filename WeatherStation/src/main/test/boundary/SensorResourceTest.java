package boundary;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SensorResourceTest {
    @Test
    @Order(1)
    public void getSensors() {
        given()
                .when()
                .get("/sensor")
                .then()
                .log()
                .body()
                .statusCode(200)
                .body("location[0]", is("Location1"),
                        "name[0]", is("Sensor1")
                );
    }

    @Test
    @Order(2)
    public void postSensor() {
        String requestBody = """
        {
            "location": "NewLocation",
            "name": "NewSensor"
        }
        """;

        given()
                .when()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/sensor")
                .then()
                .log().body().statusCode(201);
    }
}

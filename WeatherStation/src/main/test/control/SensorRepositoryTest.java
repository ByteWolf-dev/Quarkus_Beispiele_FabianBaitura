package control;

import com.github.javafaker.Faker;
import htl.leonding.control.SensorRepository;
import htl.leonding.entity.Sensor;
import io.agroal.api.AgroalDataSource;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;


import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SensorRepositoryTest {
    @Inject
    SensorRepository sensorRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    public void createSensor() {
        QuarkusTransaction.begin();

        Table sensorTable = new Table(ds, "Sensor");
        output(sensorTable).toConsole();

        Sensor sensor = new Sensor();
        sensor.setName("Name 1");
        sensor.setLocation("Location 1");

        //mir ist bewusst das ich hier eine basis function von Quarkus teste.
        sensorRepository.persist(sensor);
        QuarkusTransaction.commit();

        sensorTable = new Table(ds, "Sensor");
        output(sensorTable).toConsole();

        assertThat(sensorTable)
                .hasNumberOfRows(3)
                .row(2)
                .value("NAME").isEqualTo("Name 1")
                .value("LOCATION").isEqualTo("Location 1");
    }

    @Order(2)
    @Test
    public void createSensorWithTestData() {
        QuarkusTransaction.begin();
        Table sensorTable = new Table(ds, "Sensor");
        output(sensorTable).toConsole();

        Faker faker = new Faker();

        String name = faker.name().firstName();
        String location = faker.address().fullAddress();

        Sensor sensor = new Sensor();
        sensor.setName(name);
        sensor.setLocation(location);

        sensorRepository.persist(sensor);
        QuarkusTransaction.commit();

        sensorTable = new Table(ds, "Sensor");
        output(sensorTable).toConsole();

        assertThat(sensorTable)
                .hasNumberOfRows(4) //we already have data in the table
                .row(3)
                .value("NAME").isEqualTo(name)
                .value("LOCATION").isEqualTo(location);
    }
}

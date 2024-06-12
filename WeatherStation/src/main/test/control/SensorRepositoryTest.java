package control;

import htl.leonding.control.SensorRepository;
import htl.leonding.entity.Sensor;
import io.agroal.api.AgroalDataSource;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
public class SensorRepositoryTest {
    @Inject
    SensorRepository sensorRepository;

    @Inject
    AgroalDataSource ds;

    @Test
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
}

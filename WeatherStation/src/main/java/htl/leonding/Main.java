package htl.leonding;

import htl.leonding.controll.SensorRepository;
import htl.leonding.entity.Measurement;
import htl.leonding.entity.Sensor;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusMain
public class Main implements QuarkusApplication {
    @Inject
    SensorRepository sensorRepository;

    @Override
    @Transactional
    public int run(String... args) throws Exception {

        Sensor sensor1 = new Sensor("Sensor1", "Location1");
        Sensor sensor2 = new Sensor("Sensor2", "Location2");

        Measurement measurement1 = new Measurement(25.5, 60.0, 1013.25);
        Measurement measurement2 = new Measurement(22.0, 55.0, 1015.0);
        Measurement measurement3 = new Measurement(27.5, 62.0, 1010.0);

        measurement1.setSensor(sensor1);
        measurement2.setSensor(sensor1);
        measurement3.setSensor(sensor2);

        sensorRepository.persist(sensor1);
        sensorRepository.persist(sensor2);
        return 0;
    }
}

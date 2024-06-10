package htl.leonding.control;

import htl.leonding.entity.DTOs.Mapper;
import htl.leonding.entity.DTOs.SensorDto;
import htl.leonding.entity.Sensor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class SensorRepository implements PanacheRepository<Sensor> {
    public List<SensorDto> findAllAsDto() {
        return findAll()
                .list()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    public SensorDto getSensorByNameAsDto(String name) {
        return Mapper.toDto(getSensorByName(name));
    }

    public Sensor getSensorByName(String name) {
        Optional<Sensor> sensorOptional = findAll()
                .stream()
                .toList()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst();

        return sensorOptional.orElse(null);
    }

    public boolean existsByName(String name) {
        return getSensorByName(name) != null;
    }

    public boolean existsById(Long id) {
        Sensor s = findById(id);
        return findById(id) != null;
    }

    public Long addSensor(SensorDto sensorDto) {
        Sensor sensor = Mapper.toEntity(sensorDto);
        persist(sensor);
        return sensor.getId();
    }

    public SensorDto updateSensor(SensorDto sensorDto, Long id) {
        Sensor dbSensor = findById(id);

        dbSensor.setLocation(sensorDto.location());
        dbSensor.setName(sensorDto.name());

        return Mapper.toDto(dbSensor);
    }
}

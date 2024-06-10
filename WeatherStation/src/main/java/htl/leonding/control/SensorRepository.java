package htl.leonding.control;

import htl.leonding.entity.DTOs.Mapper;
import htl.leonding.entity.DTOs.SensorDto;
import htl.leonding.entity.Sensor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

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

    public SensorDto getSensorByName(String name) {
        Optional<Sensor> sensorOptional = findAll()
                .stream()
                .toList()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst();

        return sensorOptional.map(Mapper::toDto).orElse(null);
    }
}

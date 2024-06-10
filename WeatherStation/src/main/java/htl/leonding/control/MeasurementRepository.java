package htl.leonding.control;

import htl.leonding.entity.DTOs.Mapper;
import htl.leonding.entity.DTOs.MeasurementDto;
import htl.leonding.entity.Measurement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {
    public List<MeasurementDto> getBySensorIdWithLimit(String sensorName, int limit) {
        return findAll()
                .stream()
                .toList()
                .stream()
                .filter(measurement -> measurement
                        .getSensor()
                        .getName()
                        .equals(sensorName)
                )
                .limit(limit)
                .toList()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }
}

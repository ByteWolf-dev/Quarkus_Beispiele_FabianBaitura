package htl.leonding.entity.DTOs;

import htl.leonding.entity.Measurement;
import htl.leonding.entity.Sensor;

import java.util.stream.Collectors;

public class Mapper {
    public static SensorDto toDto(Sensor sensor){
        return new SensorDto(
                sensor.getId(),
                sensor.getName(),
                sensor.getLocation(),
                sensor.getMeasurements()
                        .stream()
                        .map(Mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    public static MeasurementDto toDto(Measurement measurement){
        return new MeasurementDto(
                measurement.getId(),
                measurement.getTemperature(),
                measurement.getHumidity(),
                measurement.getPressure()
        );
    }

    public static Sensor toEntity(SensorDto sensorDto){
        return new Sensor(sensorDto.name(), sensorDto.location());
    }
}

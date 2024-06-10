package htl.leonding.entity.DTOs;

import java.util.List;

public record SensorDto
        (
                Long id,
                String name,
                String location,
                List<MeasurementDto> measurements
        )
{

}

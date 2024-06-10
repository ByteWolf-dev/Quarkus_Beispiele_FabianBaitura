package htl.leonding.entity.DTOs;

public record MeasurementDto(
        Long id,
        double temperature,
        double humidity,
        double pressure
) {
}

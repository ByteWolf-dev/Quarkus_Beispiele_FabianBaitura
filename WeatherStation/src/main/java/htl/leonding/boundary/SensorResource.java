package htl.leonding.boundary;

import htl.leonding.controll.SensorRepository;
import htl.leonding.entity.DTOs.Mapper;
import htl.leonding.entity.Sensor;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/sensor")
public class SensorResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Path("/")
    public Response getSensors() {
        return Response.ok(sensorRepository.findAll()
                        .stream()
                        .toList()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList()))
                .build();
    }
}

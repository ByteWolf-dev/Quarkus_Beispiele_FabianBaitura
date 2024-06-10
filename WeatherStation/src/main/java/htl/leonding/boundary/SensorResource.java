package htl.leonding.boundary;

import htl.leonding.control.SensorRepository;
import htl.leonding.entity.DTOs.Mapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.stream.Collectors;

@Path("/sensor")
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Path("/")
    public Response getSensors() {
        try {
            return Response.ok(sensorRepository.findAllAsDto()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/{name}")
    public Response getSensorByName(@PathParam("name") String name) {
        try {
            return Response.ok(sensorRepository.getSensorByName(name)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

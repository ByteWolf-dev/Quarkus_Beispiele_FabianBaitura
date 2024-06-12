package htl.leonding.boundary;

import htl.leonding.control.SensorRepository;
import htl.leonding.entity.DTOs.SensorDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
            return Response.ok(sensorRepository.getSensorByNameAsDto(name)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/")
    @Transactional
    public Response createSensor(SensorDto sensorDto) {
        try {
            if(sensorRepository.existsByName(sensorDto.name())) {
                return Response.status(Response.Status.CONFLICT).build();
            }
            return Response
                    .status(Response.Status.CREATED)
                    .entity(sensorRepository.addSensor(sensorDto))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateSensor(@PathParam("id") Long id, SensorDto sensorDto) {
        try {
            if(!sensorRepository.existsById(id)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(sensorRepository.updateSensor(sensorDto, id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

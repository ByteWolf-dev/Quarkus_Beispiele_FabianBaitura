package htl.leonding.boundary;

import htl.leonding.control.SensorRepository;
import htl.leonding.entity.DTOs.SensorDto;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sensor")
public class SensorResource {

    @Inject
    SensorRepository sensorRepository;

    @Inject
    @Location("pub/postSensor.html")
    Template postSensorTemplate;

    @GET
    @Path("/postSensor")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getSensorTemplate() {
        return postSensorTemplate.instance();
    }

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

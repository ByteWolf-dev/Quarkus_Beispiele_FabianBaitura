package htl.leonding.boundary;

import htl.leonding.control.MeasurementRepository;
import htl.leonding.entity.DTOs.MeasurementDto;
import htl.leonding.entity.Measurement;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/measurement")
@Produces(MediaType.APPLICATION_JSON)
public class MeasurementResource {
    @Inject
    MeasurementRepository measurementRepository;

    @GET
    @Path("/")
    public Response getMeasurements(
            @QueryParam("sensorName") String sensorName,
            @QueryParam("limit") int limit)
    {
        try {
            if(sensorName == null || limit < 1) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            return Response.ok(measurementRepository.getBySensorIdWithLimit(sensorName, limit)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

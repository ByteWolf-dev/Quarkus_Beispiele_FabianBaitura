package htl.leonding.boundary;

import htl.leonding.control.EnvService;
import htl.leonding.control.ResearcherRepository;
import htl.leonding.control.Validator.GenericValidator;
import htl.leonding.control.Validator.ValidatorResponse;
import htl.leonding.entity.Researcher;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

@Path("/researcher")
public class ResearcherResource {
    @Inject
    ResearcherRepository researcherRepository;

    @Inject
    EnvService envService;

    @GET
    @Path("/")
    public Response getResearchers() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Researcher.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            StringWriter stringWriter = new StringWriter();

            List<Researcher> researchers = researcherRepository
                    .findAll()
                    .stream()
                    .toList();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            researchers.forEach(researcher -> {
                try {
                    jaxbMarshaller.marshal(researcher, stringWriter);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            });
            return Response.ok(stringWriter.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/")
    @Transactional
    public Response createResearcher(Researcher researcher) {
        try {
            ValidatorResponse validatorResponse = GenericValidator.validate(researcher);
            if (validatorResponse.isValid()) {
                researcherRepository.persist(researcher);
                return Response.status(Response.Status.CREATED).entity(researcher).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(validatorResponse).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/env")
    public Response envFileTest() {
        return Response.status(Response.Status.ACCEPTED).entity(envService.getGreeting()).build();
    }
}

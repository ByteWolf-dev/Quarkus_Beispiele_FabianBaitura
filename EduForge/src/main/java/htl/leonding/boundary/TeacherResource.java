package htl.leonding.boundary;

import htl.leonding.control.TeacherRepository;
import htl.leonding.entity.Teacher;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.List;

@Path("/teacher")
public class TeacherResource {
    @Inject
    TeacherRepository teacherRepository;

    @GET
    @Path("/")
    public Response getTeachers() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Teacher.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            List<Teacher> teachers = teacherRepository.findAll()
                    .stream()
                    .toList();

            StringWriter sw = new StringWriter();
            teachers.forEach(teacher -> {
                try {
                    marshaller.marshal(teacher, sw);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            });
            return Response.ok(sw.toString()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

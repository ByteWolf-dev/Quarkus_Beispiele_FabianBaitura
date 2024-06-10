package htl.leonding.boundary;

import htl.leonding.control.StudentRepository;
import htl.leonding.entity.Student;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/student")
public class StudentResource {
    @Inject
    StudentRepository studentRepository;


    @GET
    @Path("/")
    public Response getStudents() {
        return Response.ok(studentRepository.findAll()).build();
    }
}

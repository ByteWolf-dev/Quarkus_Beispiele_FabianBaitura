package htl.leonding.boundary;

import htl.leonding.control.StudentRepository;
import htl.leonding.control.Validating.GenericValidator;
import htl.leonding.control.Validating.ValidationResponse;
import htl.leonding.entity.Student;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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

    @POST
    @Path("/")
    @Transactional
    public Response createStudent(Student student) {
        ValidationResponse validationResponse = GenericValidator.validate(student);
        try {
            if (validationResponse.isValid()) {
                studentRepository.persist(student);
                return Response.status(Response.Status.CREATED).build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(validationResponse).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}

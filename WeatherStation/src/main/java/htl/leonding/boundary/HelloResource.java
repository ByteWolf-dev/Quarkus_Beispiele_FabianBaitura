package htl.leonding.boundary;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
    @Inject
    @Location("pub/hello.html")
    Template hello;

    @GET
    @Path("/{name}")
    public TemplateInstance getHelloTemplate(@PathParam("name") String name) {
        return hello.data("name", name);
    }
}

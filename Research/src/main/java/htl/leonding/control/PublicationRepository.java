package htl.leonding.control;

import htl.leonding.entity.Publication;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublicationRepository implements PanacheRepository<Publication> {
}

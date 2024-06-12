package htl.leonding.control;

import htl.leonding.entity.Researcher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResearcherRepository implements PanacheRepository<Researcher> {
}

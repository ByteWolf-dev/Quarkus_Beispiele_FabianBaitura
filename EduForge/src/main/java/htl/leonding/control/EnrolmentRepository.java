package htl.leonding.control;

import htl.leonding.entity.Enrolment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnrolmentRepository implements PanacheRepository<Enrolment> {
}

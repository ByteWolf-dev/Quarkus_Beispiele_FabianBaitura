package htl.leonding.control;

import htl.leonding.entity.StudentContactInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentContactInfoRepository implements PanacheRepository<StudentContactInfo> {
}

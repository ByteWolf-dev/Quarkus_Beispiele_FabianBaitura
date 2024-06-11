package htl.leonding.control;

import htl.leonding.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {
}

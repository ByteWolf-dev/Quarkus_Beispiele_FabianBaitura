package htl.leonding.controll;

import htl.leonding.entity.Measurement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {

}

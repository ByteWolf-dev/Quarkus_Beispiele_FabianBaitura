package htl.leonding.control;

import htl.leonding.entity.Researcher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class ResearcherRepository implements PanacheRepository<Researcher> {
    public Researcher getByFirstNameAndLastName(String firstName, String lastName) {

        firstName = firstName.replaceAll("\"", "");
        lastName = lastName.replaceAll("\"", "");

        TypedQuery<Researcher> researcherTypedQuery = getEntityManager().createNamedQuery("findResearcherByName", Researcher.class);
        researcherTypedQuery.setParameter("firstName", firstName);
        researcherTypedQuery.setParameter("lastName", lastName);
        Researcher researchers = researcherTypedQuery.getSingleResult();
        return researchers;
    }
}

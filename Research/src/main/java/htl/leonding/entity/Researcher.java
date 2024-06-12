package htl.leonding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "R_Researcher")
@XmlRootElement
@NamedQueries(
        @NamedQuery(name = "findResearcherByName",
                query = "SELECT r FROM Researcher r " +
                        "WHERE r.firstName = :firstName " +
                        "AND r.lastName = :lastName")
)
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name can't be null")
    private String firstName;

    @NotNull(message = "Last Name can't be null")
    private String lastName;

    @NotNull(message = "Email can't be null")
    @Email(message = "Email is invalid")
    private String email;

    @ManyToMany()
    @JoinTable(
            name = "R_Research_Publication",
            joinColumns = @JoinColumn(name = "researcherId"),
            inverseJoinColumns = @JoinColumn(name = "publicationId")
    )
    private List<Publication> publications = new ArrayList<>();

    public Researcher(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Researcher() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.getResearchers().add(this);
    }
}

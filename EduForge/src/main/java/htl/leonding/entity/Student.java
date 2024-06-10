package htl.leonding.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne
    private StudentContactInfo contactInfo;

    @OneToMany(mappedBy = "student")
    private List<Enrolment> enrolments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public StudentContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(StudentContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Enrolment> getEnrollments() {
        return enrolments;
    }

    public void setEnrollments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
}

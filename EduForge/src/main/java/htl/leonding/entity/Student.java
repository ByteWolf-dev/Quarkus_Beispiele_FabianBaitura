package htl.leonding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    //#region Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private StudentContactInfo contactInfo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrolment> enrolments = new ArrayList<>();

    //#endregion

    //#region Constructors

    public Student(String firstName, String lastName, StudentContactInfo contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setContactInfo(contactInfo);
    }

    public Student() {}

    //#endregion

    //#region Getters/Setters

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(StudentContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        contactInfo.setStudent(this);
    }

    public List<Enrolment> getEnrollments() {
        return enrolments;
    }

    public void setEnrollments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    //#endregion
}

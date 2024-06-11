package htl.leonding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class StudentContactInfo {
    //region properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "contactInfo")
    private Student student;

    @NotNull(message = "Phone Number cannot be null")
    private String phoneNumber;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email is invalid")
    private String email;

    //endregion

    //region constructor
    public StudentContactInfo(String email, String phoneNumber, Student student) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.setStudent(student);
    }

    public StudentContactInfo() {}
    //endregion

    //region getter/setter
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        student.setContactInfo(this);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //endregion
}

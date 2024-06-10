package htl.leonding.entity;

import jakarta.persistence.*;

@Entity
public class StudentContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "contactInfo")
    private Student student;

    private String phoneNumber;

    private String email;

    public StudentContactInfo(String email, String phoneNumber, Student student) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.setStudent(student);
    }

    public StudentContactInfo() {}

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
}

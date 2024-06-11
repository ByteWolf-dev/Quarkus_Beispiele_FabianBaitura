package htl.leonding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
public class Enrolment {

    //#region Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student cannot be null")
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @NotNull(message = "Course cannot be null")
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @NotNull(message = "Message should not be null")
    @PastOrPresent(message = "Date should be in the past or present")
    private LocalDateTime enrollmentDate;

    //#endregion

    //#region Constructors

    public Enrolment(Student student, Course course, LocalDateTime enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrolment() {}

    //#endregion

    //#region Getters/Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        student.getEnrollments().add(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        course.getEnrollments().add(this);
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    //#endregion
}

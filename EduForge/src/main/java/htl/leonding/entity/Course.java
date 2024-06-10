package htl.leonding.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    private String title;

    private String description;

    @OneToMany(mappedBy = "course")
    private List<Enrolment> enrollments = new ArrayList<>();

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Course() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getCourses().add(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Enrolment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrolment> enrolments) {
        this.enrollments = enrolments;
    }
}

package jpaschoolproject.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Education {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    private String name;

    @Basic
    private final String schoolName = "IT-högskolan";

    @Basic
    private double creditScore;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "education")
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Course> courses;

    protected Education(){}

    public Education(String name, double creditScore) {
        this.name = name;
        this.creditScore = creditScore;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

//    public void setSchoolName(String schoolName) {
//        this.schoolName = schoolName;
//    } 

    public double getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        getStudents().add(student);
        student.setEducation(this);
    }

    public void removeStudent(Student student) {
        getStudents().remove(student);
        student.setEducation(null);
    }

    public List<Course> getCourses() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        getCourses().add(course);
        course.getEducations().add(this);
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
        course.getEducations().remove(this);
    }

}
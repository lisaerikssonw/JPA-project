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

@Entity
public class Course {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    private String name;

    @Basic
    private int weekLength;

    @Basic
    private double creditScore;

    @ManyToMany(mappedBy = "courses")
    private List<Education> educations;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Teacher> teachers;

    protected Course(){}
    
    public Course(String name, int weekLength, double creditScore) {
        this.name = name;
        this.weekLength = weekLength;
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

    public int getWeekLength() {
        return this.weekLength;
    }

    public void setWeekLength(int weekLength) {
        this.weekLength = weekLength;
    }

    public double getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public List<Education> getEducations() {
        if (educations == null) {
            educations = new ArrayList<>();
        }
        return this.educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public void addEducation(Education education) {
        getEducations().add(education);
    }

    public void removeEducation(Education education) {
        getEducations().remove(education);
    }

    public List<Teacher> getTeachers() {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        return this.teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        getTeachers().add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        getTeachers().remove(teacher);
        teacher.getCourses().remove(this);
    }

}
package jpaschoolproject.domain;

import utility.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Teacher {

    @Column(unique = true, nullable = false)
    @Id
    private Long persId;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private int salary;

    @Basic
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    private String nationality;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> courses;
    
    protected Teacher(){}

    public Teacher(Long persId, String firstName, String lastName, int salary, Gender gender, String nationality) {
        this.persId = persId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.gender = gender;
        this.nationality = nationality;
    }

    public Long getPersId() {
        return this.persId;
    }

    public void setPersId(Long persId) {
        this.persId = persId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
    }

}
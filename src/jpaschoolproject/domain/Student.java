package jpaschoolproject.domain;

import utility.Gender;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Student {

    @Column(unique = true, nullable = false)
    @Id
    private Long persId;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    private String nationality;

    @Basic
    private double grade;

    @ManyToOne
    private Education education;
    
    protected Student(){}
    
    public Student(Long persId, String firstName, String lastName, Gender gender, String nationality, double grade) {
        this.persId = persId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.grade = grade;

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

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Education getEducation() {
        return this.education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

}
package jpaschoolproject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpaschoolproject.domain.Student;
import java.util.List;
import javax.persistence.TypedQuery;
import jpaschoolproject.domain.Education;
import utility.Gender;

public class StudentMgmt {

    public static void addStudent() {

        Long ssn = UI.readLong("\nSocial security number (YYMMDDXXXX): ");
        String firstName = UI.readString("First name: ");
        String lastName = UI.readString("Last name: ");
        String gender = UI.readString("Gender (Male/Female/Other): ").toUpperCase();
        String nationality = UI.readString("Nationality: ");
        double grade = UI.readDouble("Grade (1.0-5.0): ");

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(new Student(ssn, firstName, lastName, Gender.valueOf(gender), nationality, grade));
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nAdd student error " + e);
        }
    }

    public static void removeStudent(Long ssn) {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student stud = em.find(Student.class, ssn);
            if(UI.optionCheck("\nAre you sure you want to remove "+stud.getFirstName()+" "+stud.getLastName()+" (Y/N)? ")) {
                em.remove(stud);
                em.getTransaction().commit();
                System.out.println(stud.getFirstName() + " "+stud.getLastName()+" has now been removed.");
            } else {
                System.out.println("\nThe student was not removed");
            }
            
        } catch (Exception e) {
            System.out.println("Remove student error " + e);
        }

        em.close();
    }

    public static List<Student> showAll() {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();

        Query q = em.createQuery("SELECT s from Student s");
        List<Student> list = q.getResultList();

        em.close();

        return list;
    }

    public static void printList(List<Student> list) {
        if (list.size() == 0) {
            System.out.println("Found no matches");
        } else {
            printHeader();

            for (Student s : list) {
                if (s.getEducation() == null) {

                    System.out.println(UI.fixStringLength(s.getPersId(), 13) + UI.fixStringLength(s.getFirstName(), 14)
                            + UI.fixStringLength(s.getLastName(), 14) + UI.fixStringLength(s.getGender(), 9)
                            + UI.fixStringLength(s.getNationality(), 13) + UI.fixStringLength(s.getGrade(), 8) + "-");
                }
                else if (s.getEducation() != null) {
                    System.out.println(UI.fixStringLength(s.getPersId(), 13) + UI.fixStringLength(s.getFirstName(), 14)
                            + UI.fixStringLength(s.getLastName(), 14) + UI.fixStringLength(s.getGender(), 9)
                            + UI.fixStringLength(s.getNationality(), 13) + UI.fixStringLength(s.getGrade(), 8)
                            + UI.fixStringLength(s.getEducation().getName(), 20));
                    
                }
            }
        }
    }

    public static void printStudent(Student s) {
        
        printHeader();
        
        if(s.getEducation() == null) {
            System.out.println(UI.fixStringLength(s.getPersId(), 13) + UI.fixStringLength(s.getFirstName(), 14)
                + UI.fixStringLength(s.getLastName(), 14) + UI.fixStringLength(s.getGender(), 9)
                + UI.fixStringLength(s.getNationality(), 13) + UI.fixStringLength(s.getGrade(), 8) + "-");
        } else if(s.getEducation() != null) {
            System.out.println(UI.fixStringLength(s.getPersId(), 13) + UI.fixStringLength(s.getFirstName(), 14)
                + UI.fixStringLength(s.getLastName(), 14) + UI.fixStringLength(s.getGender(), 9)
                + UI.fixStringLength(s.getNationality(), 13) + UI.fixStringLength(s.getGrade(), 8)
                + UI.fixStringLength(s.getEducation().getName(), 20));
        }
        
    }

    private static void printHeader() {
        System.out.println("\nSSN          First name    Last name     Gender   Nationality  Grade   Education           ");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static List<Student> findByName(String name) {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();

        TypedQuery q = em.createQuery("SELECT a from Student a WHERE a.firstName LIKE CONCAT('%',:name,'%') OR a.lastName LIKE CONCAT('%',:name,'%')", Student.class);
        q.setParameter("name", name);

        List<Student> list = q.getResultList();

        em.close();

        return list;
    }

    public static Student findBySsn(Long ssn) {

        Student s = null;

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            s = em.find(Student.class, ssn);

            em.close();
        } catch (Exception e) {
            System.out.println("Find student by SSN error " + e);
        }

        return s;
    }

    public static void updateFirstName(Long ssn, String newName) {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();
        Student s = em.find(Student.class, ssn);

        try {
            s.setFirstName(newName);
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();

            System.out.println("\nUpdate successful!");
            printStudent(findBySsn(ssn));

            em.close();
        } catch (Exception e) {
            System.out.println("\nFirst name could not be updated");
        }
    }

    public static void updateLastName(Long ssn, String newName) {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();
        Student s = em.find(Student.class, ssn);

        try {
            s.setLastName(newName);
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();

            System.out.println("\nUpdate successful!");
            printStudent(findBySsn(ssn));

            em.close();
        } catch (Exception e) {
            System.out.println("\nLast name could not be updated");
        }
    }

    public static void updateGrade(Long ssn, double newGrade) {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();
        Student s = em.find(Student.class, ssn);

        try {
            s.setGrade(newGrade);
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();

            System.out.println("\nUpdate successful!");
            printStudent(findBySsn(ssn));

            em.close();
        } catch (Exception e) {
            System.out.println("\nGrade could not be updated");
        }
    }

    public static void assignToEducation(Student s) {
        EducationMgmt.printList(EducationMgmt.showAll());

        int choice = UI.readInt("\nEnter ID of the education you want to assign: ");

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();

            Education e = em.find(Education.class, choice);
            s.setEducation(e);
            em.merge(s);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nAssign student to education error " + e);
        }

    }

}

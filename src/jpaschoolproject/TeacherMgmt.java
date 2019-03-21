package jpaschoolproject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpaschoolproject.domain.Teacher;
import java.util.List;
import javax.persistence.TypedQuery;
import jpaschoolproject.domain.Course;
import utility.Gender;

public class TeacherMgmt {

    public static void addTeacher() {
        Long ssn = UI.readLong("\nSocial security number (YYMMDDXXXX): ");
        String firstName = UI.readString("First name: ");
        String lastName = UI.readString("Last name: ");
        int salary = UI.readInt("Salary: ");
        String gender = UI.readString("Gender (Male/Female/Other): ").toUpperCase();
        String nationality = UI.readString("Nationality: ");

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(new Teacher(ssn, firstName, lastName, salary, Gender.valueOf(gender), nationality));
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nAdd teacher error " + e);
        }
    }

    public static List<Teacher> showAll() {
        List<Teacher> list = null;
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();

            Query q = em.createQuery("SELECT a FROM Teacher a");
            list = q.getResultList();
            em.close();

        } catch (Exception e) {
            System.out.println("\nShow all error " + e);
        }
        return list;
    }

    public static void printList(List<Teacher> list) {
        if (list.size() == 0) {
            System.out.println("Found no matches");
        } else {

            printHeader();

            for (Teacher t : list) {
                System.out.println(UI.fixStringLength(t.getPersId(), 13) + UI.fixStringLength(t.getFirstName(), 14)
                        + UI.fixStringLength(t.getLastName(), 14) + UI.fixStringLength(t.getGender(), 9)
                        + UI.fixStringLength(t.getNationality(), 13) + UI.fixStringLength(t.getSalary(), 7));
            }
        }
    }

    private static void printHeader() {
        System.out.println("\nSSN          First name    Last name     Gender   Nationality  Salary ");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public static List<Teacher> findByName(String name) {
        List<Teacher> list = null;
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();

            TypedQuery q = em.createQuery("SELECT a from Teacher a WHERE a.firstName LIKE CONCAT('%',:name,'%') OR a.lastName LIKE CONCAT('%',:name,'%')", Teacher.class);
            q.setParameter("name", name);
            list = q.getResultList();

            em.close();
        } catch (Exception e) {
            System.out.println("Search teacher by name error " + e);
        }
        return list;
    }
    
    public static void printTeacher(Teacher t) {
        
        printHeader();
        
        System.out.println(UI.fixStringLength(t.getPersId(), 13) + UI.fixStringLength(t.getFirstName(), 14)
                        + UI.fixStringLength(t.getLastName(), 14) + UI.fixStringLength(t.getGender(), 9)
                        + UI.fixStringLength(t.getNationality(), 13) + UI.fixStringLength(t.getSalary(), 7));
        
    }

    public static Teacher findBySsn(Long ssn) {

        Teacher t = null;

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            t = em.find(Teacher.class, ssn);

            em.close();
        } catch (Exception e) {
            System.out.println("Find teacher by SSN error " + e);
        }

        return t;
    }

    public static void removeTeacher(Long ssn) {
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            Teacher t = em.find(Teacher.class, ssn);
            //Print teacher - Are you sure you want to remove?
            em.remove(t);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("Remove teacher error " + e);
        }
    }

    public static void updateFirstName(Long ssn, String newName) {
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            Teacher t = em.find(Teacher.class, ssn);
            t.setFirstName(newName);
            em.merge(t);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nUpdate first name error " + e);
        }
    }

    public static void updateLastName(Long ssn, String newName) {
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            Teacher t = em.find(Teacher.class, ssn);
            t.setLastName(newName);
            em.merge(t);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nUpdate last name error " + e);
        }
    }

    public static void updateSalary(Long ssn, int newSalary) {
        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            Teacher t = em.find(Teacher.class, ssn);
            t.setSalary(newSalary);
            em.merge(t);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nUpdate salary error " + e);
        }
    }
    
    public static void assignToCourse(Teacher t) {
        CourseMgmt.printList(CourseMgmt.showAll());

        int choice = UI.readInt("\nEnter ID of the course you want to assign: ");

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();

            Course c = em.find(Course.class, choice);
            t.addCourse(c);
            em.merge(t);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nAssign teacher to course error " + e);
        }

    }

}

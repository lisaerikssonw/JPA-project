package jpaschoolproject;

import java.util.List;
import javax.persistence.EntityManager;
import jpaschoolproject.domain.Education;
import javax.persistence.Query;

public class EducationMgmt {

    public static void addEducation() {
        String name = UI.readString("\nEducation name: ");
        double creditScore = UI.readDouble("Credit score: ");

        try {
            EntityManager em = JpaSchoolProject.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(new Education(name, creditScore));
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println("\nAdd education error " + e);
        }
    }

    public static List<Education> showAll() {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();

        Query q = em.createQuery("SELECT a from Education a");
        List<Education> list = q.getResultList();

        em.close();

        return list;
    }

    public static void printList(List<Education> list) {
        
        if (list.size() == 0) {
            System.out.println("Found no matches");
        } else {
            printHeader();

            for (Education e : list) {
                System.out.println(UI.fixStringLength(e.getId(), 4) + UI.fixStringLength(e.getName(), 20) + 
                        UI.fixStringLength(e.getSchoolName(), 15) + UI.fixStringLength(e.getCreditScore(), 12));
            }
        }
    }
    
    private static void printHeader() {
        System.out.println("\nID  Name                School         Credit score");
        System.out.println("---------------------------------------------------");
    }

}

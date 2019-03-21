package jpaschoolproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jpaschoolproject.domain.Course;

public class CourseMgmt {
    
    public static void printList(List<Course> list) {
        printHeader();
        for (Course c : list) {
            System.out.println(UI.fixStringLength(c.getId(), 4) + UI.fixStringLength(c.getName(), 20) + 
                    UI.fixStringLength(c.getWeekLength(), 17) + UI.fixStringLength(c.getCreditScore(), 12));
        }
    }

    private static void printHeader() {
        System.out.println("\nID  Name                Length (weeks)   Credit score");
        System.out.println("-----------------------------------------------------");
    }
    
    public static List<Course> showAll() {
        EntityManager em = JpaSchoolProject.emf.createEntityManager();

        Query q = em.createQuery("SELECT c from Course c");
        List<Course> list = q.getResultList();

        em.close();

        return list;
    }
}

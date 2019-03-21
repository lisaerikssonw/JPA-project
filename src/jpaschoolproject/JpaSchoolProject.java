package jpaschoolproject;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaSchoolProject {

    static boolean loop = true;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DEFAULT_PU");

    public static void main(String[] args) {

        while (loop) {
            UI.switchChoice(UI.mainMenu());
        }

        emf.close();
    }

}

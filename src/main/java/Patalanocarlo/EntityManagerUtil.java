package Patalanocarlo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf= Persistence.createEntityManagerFactory("Exam-d6-w3");
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}

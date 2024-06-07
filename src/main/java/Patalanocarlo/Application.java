package Patalanocarlo;

import jakarta.persistence.EntityManager;


public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
    }
}

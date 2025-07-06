package com.example.BankCardManagementSystems.repository;


import com.example.BankCardManagementSystems.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final EntityManagerFactory entityManagerFactory;

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }




















//    public void insertUser(User user) {
//        if (user == null) {
//            throw new IllegalArgumentException("User cannot be null");
//        }
//
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction tx = null;
//        try {
//            tx = em.getTransaction();
//            tx.begin();
//            em.persist(user);
//            tx.commit();
//        } catch (Exception ex) {
//            if (tx != null && tx.isActive()) {
//                tx.rollback();
//            }
//            throw new RuntimeException("Failed to save user", ex);
//        } finally {
//            em.close();
//        }
//    }


}

package com.example.BankCardManagementSystems.repository;

import com.example.BankCardManagementSystems.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.BankCardManagementSystems.log.PrintLog.*;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    public List<User> findAllUsers(){
        try{
            startLogTransaction("findAllUsers");

            List<User> result = createQueryFindAllUsers();

            theEndLogTransaction("findAllUsers");
            return result;
        } catch (RuntimeException e) {
            throw new DataAccessException("Failed to retrieve users", e){};
        }
    }

    private List<User> createQueryFindAllUsers(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    public User findById(String id){
        try {
            startLogTransaction("findById");

            User user = createQueryFindById(id);

            theEndLogTransaction("findById");
            return user;
        }catch (RuntimeException ex){
            throw new RuntimeException(ex);
        }
    }

    private User createQueryFindById(String UserId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("id"), UserId));

        return entityManager.createQuery(cq).getSingleResult();
    }

    public void saveUser(User user){
        try {
            startLogTransaction("saveUser");

            entityManager.persist(user);

            theEndLogTransaction("saveUser");
        }catch (RuntimeException ex){
            throw new RuntimeException(ex);
        }
    }

    public void updateUser(User user){
        try {
            startLogTransaction("updateUser");

            entityManager.merge(user);

            theEndLogTransaction("updateUser");
        }catch (RuntimeException ex){
            throw new RuntimeException(ex);
        }
    }






}

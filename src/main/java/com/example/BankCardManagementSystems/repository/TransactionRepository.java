package com.example.BankCardManagementSystems.repository;

import com.example.BankCardManagementSystems.entity.Transaction;
import com.example.BankCardManagementSystems.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.BankCardManagementSystems.log.PrintLog.*;

@Repository
@Transactional
public class TransactionRepository {

    private final EntityManagerFactory entityManagerFactory;

    public TransactionRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Transaction> findAllTransactions() {
        EntityManager em = null;
        try {
            em = createEntityManager();
            startLogTransaction("findAllTransactions");

            List<Transaction> result = criteriaQueryFindAllTransaction(em);

            theEndLogTransaction("findAllTransactions");
            return result;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error fetching findAllTransactions", ex);
        } finally {
            assert em != null;
            em.close();
            CloseEntityManager("findAllTransactions");
        }
    }

    private EntityManager createEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    private List<Transaction> criteriaQueryFindAllTransaction(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        cq.select(root);

        processLogTransactionByAll("criteriaQueryFindAllTransaction");
        return em.createQuery(cq).getResultList();
    }

    public Transaction findById(String id){
        EntityManager em = createEntityManager();
        try{
            startLogTransaction("findById");

            Transaction tr = criteriaQueryFindById(em, id);

            theEndLogTransaction("findById");
            return tr;
        }catch(RuntimeException ex){
            throw new RuntimeException(ex);
        }finally{
            em.close();

        }
    }

    private Transaction criteriaQueryFindById(EntityManager em, String id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        cq.select(root).where(cb.equal(root.get("id"), id));

        processLogTransactionById("criteriaQueryFindById");
        return em.createQuery(cq).getSingleResult();
    }

    public void saveTransaction(Transaction transaction){
        EntityManager em = createEntityManager();
        try {
            startLogTransaction("saveTransaction");

            em.persist(transaction);

            theEndLogTransaction("saveTransaction");
        }catch (RuntimeException ex){
            throw new RuntimeException(ex);
        }finally {
            em.close();
            CloseEntityManager("saveTransaction");
        }
    }

    public void updateTransaction(Transaction transaction){
        EntityManager em = createEntityManager();
        try {
            startLogTransaction("updateTransaction");

            em.merge(transaction);

            theEndLogTransaction("updateTransaction");
        }catch (RuntimeException ex){
            throw new RuntimeException(ex);
        } finally {
            em.close();
            CloseEntityManager("updateTransaction");
        }
    }

    public void deleteTransaction(String id){
        EntityManager em = createEntityManager();
        try{
            startLogTransaction("deleteTransaction");

            em.remove(findById(id));

            theEndLogTransaction("deleteTransaction");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            CloseEntityManager("deleteTransaction");
        }
    }
}
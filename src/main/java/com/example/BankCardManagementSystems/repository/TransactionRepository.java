package com.example.BankCardManagementSystems.repository;

import com.example.BankCardManagementSystems.entity.Transaction;
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

@Repository
@Transactional
public class TransactionRepository {

    private final EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    public TransactionRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Transaction> findAllTransactions(){
        try (EntityManager em = createEntityManager()) {
            startTransactionPrint("findAllTransactions");

            List<Transaction> result = criteriaQueryFindAllTransaction(em);

            theEndTransactionPrint("findAllTransactions");
            return result;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error fetching findAllTransactions", ex);
        } finally {
            logger.debug("Closing EntityManager ");
        }
    }

    private EntityManager createEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    private void startTransactionPrint(String method){
        logger.debug("Starting {}() - Creating transaction and query", method);
    }

    private List<Transaction> criteriaQueryFindAllTransaction(EntityManager em){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        cq.select(root);

        logger.debug("Executing JPA query to fetch all findAllTransactions");
        return em.createQuery(cq).getResultList();
    }

    private void theEndTransactionPrint(String method){
        logger.debug("Transaction committed successfully. Retrieved {}", method);
    }
}

package com.example.BankCardManagementSystems.repository;

import com.example.BankCardManagementSystems.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.QueryTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepository {

    private final EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(CardRepository.class);

    public CardRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Card> findAllCards(){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try {
            logger.debug("Starting findAllCards() - Creating transaction and query");
            et = em.getTransaction();
            et.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Card> cq = cb.createQuery(Card.class);
            Root<Card> root = cq.from(Card.class);
            cq.select(root);

            logger.debug("Executing JPA query to fetch all cards");
            List<Card> result = em.createQuery(cq).getResultList();

            et.commit();
            logger.debug("Transaction committed successfully. Retrieved {} cards", result.size());
            return result;
        }catch (RuntimeException ex) {
            if (et != null && et.isActive()) {
                try {
                    et.rollback();
                    logger.error("Transaction rolled back due to error");
                } catch (Exception rollbackEx) {
                    logger.error("Failed to rollback transaction", rollbackEx);
                }
            }
            if (ex instanceof QueryTimeoutException) {
                throw new QueryTimeoutException("Error timeout", ex);
            } else {
                throw new RuntimeException("Error fetching cards", ex);
            }
        }finally{
            if(em.isOpen()){
                logger.debug("Closing EntityManager ");
                em.close();
            }
        }
    }

    public Optional<Card> findById(String cardId){
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            logger.debug("Starting findById(...) - search card");

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Card> cq = cb.createQuery(Card.class);
            Root<Card> root = cq.from(Card.class);

            cq.select(root).where(cb.equal(root.get("id"), cardId));

            Card card = em.createQuery(cq).getSingleResult();

            Optional<Card> result = Optional.ofNullable(card);

            return result;
        }catch (RuntimeException ex){
            throw new DataAccessException("Not found card with ID: " + cardId, ex) {};
        }finally {
            em.close();
        }
    }

    public void insertCard(Card card) throws DataAccessException {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try {
            logger.debug("Starting insertCard(...) - saved card");
            et = em.getTransaction();
            et.begin();

            em.persist(card);

            et.commit();
            logger.debug("Transaction commited successfully");
        }catch (RuntimeException ex){
            if (et != null && et.isActive()) {
                try {
                    et.rollback();
                    logger.error("Transaction rolled back due to error");
                } catch (Exception rollbackEx) {
                    logger.error("Failed to rollback transaction", rollbackEx);
                }
            }
            throw new DataAccessException("Failed to save card: " + card.getId(), ex){};
        }finally {
            if(em.isOpen()) {
                logger.debug("Closing EntityManager");
                em.close();
            }
        }
    }

    public void updateStatusCard(Card card){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        try {
            logger.debug("Starting updateStatusCard(...) - updated status card");
            et = em.getTransaction();
            et.begin();

            em.merge(card);

            et.commit();
            logger.debug("Transaction commited successfully");
        }catch (RuntimeException ex){
            if (et != null && et.isActive()) {
                try {
                    et.rollback();
                    logger.error("Transaction rolled back due to error");
                } catch (Exception rollbackEx) {
                    logger.error("Failed to rollback transaction", rollbackEx);
                }
            }
            throw new DataAccessException("Failed to update card status. Card ID: " + card.getId(), ex){};
        }finally {
            if (em.isOpen()) {
                logger.debug("Closing EntityManager");
                em.close();
            }
        }
    }

    public void deleteCard(Card card) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(card) ? card : em.merge(card));
            em.getTransaction().commit();
            logger.debug("Deleted card successfully");
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Error deleting card", ex);
            throw ex;
        } finally {
            em.close();
        }
    }
}

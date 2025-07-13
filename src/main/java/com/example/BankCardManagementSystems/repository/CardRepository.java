package com.example.BankCardManagementSystems.repository;

import com.example.BankCardManagementSystems.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.QueryTimeoutException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CardRepository {

    @Autowired
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CardRepository.class);

    public List<Card> findAllCards(){
        try {
            logger.debug("Starting findAllCards() - Creating transaction and query");

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Card> cq = cb.createQuery(Card.class);
            Root<Card> root = cq.from(Card.class);
            cq.select(root);

            logger.debug("Executing JPA query to fetch all cards");
            List<Card> result = entityManager.createQuery(cq).getResultList();

            logger.debug("Transaction committed successfully. Retrieved {} cards", result.size());
            return result;
        }catch (RuntimeException ex) {
            throw new RuntimeException("Error fetching cards", ex);
        }
    }

    public Optional<Card> findById(String cardId){
        try {
            logger.debug("Starting findById(...) - search card");

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Card> cq = cb.createQuery(Card.class);
            Root<Card> root = cq.from(Card.class);

            cq.select(root).where(cb.equal(root.get("id"), cardId));

            Card card = entityManager.createQuery(cq).getSingleResult();

            return Optional.ofNullable(card);
        }catch (RuntimeException ex){
            throw new DataAccessException("Not found card with ID: " + cardId, ex) {};
        }
    }

    public void insertCard(Card card) throws DataAccessException {
        try {
            logger.debug("Starting insertCard(...) - saved card");

            entityManager.persist(card);

            logger.debug("Transaction commited successfully");
        }catch (RuntimeException ex){
            throw new DataAccessException("Failed to save card: " + card.getId(), ex){};
        }
    }

    public void updateStatusCard(Card card){
        try {
            logger.debug("Starting updateStatusCard(...) - updated status card");

            entityManager.merge(card);

            logger.debug("Transaction commited successfully");
        }catch (RuntimeException ex){
            throw new DataAccessException("Failed to update card status. Card ID: " + card.getId(), ex){};
        }
    }
}

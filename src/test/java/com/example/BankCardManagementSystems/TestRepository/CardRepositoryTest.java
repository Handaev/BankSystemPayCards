package com.example.BankCardManagementSystems.TestRepository;

import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.repository.CardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.QueryTimeoutException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardRepositoryTest {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager em;

    private EntityTransaction et;

    private CriteriaBuilder cb;

    private CriteriaQuery<Card> cq;

    private Root<Card> root;

    private TypedQuery<Card> typedQuery;

    private CardRepository cardRepository;

    @BeforeEach
    void addBeforeMock(){
        entityManagerFactory = mock(EntityManagerFactory.class);
        em = mock(EntityManager.class);
        et = mock(EntityTransaction.class);
        cb = mock(CriteriaBuilder.class);
        cq = mock(CriteriaQuery.class);
        root = mock(Root.class);
        typedQuery = mock(TypedQuery.class);
    }

    @AfterEach
    void addAlterVerify(){
        Mockito.verify(et, Mockito.times(1)).begin();
        Mockito.verify(et, times(1)).commit();
    }

    @Test
    @DisplayName("standard check")
    void findAllCards_Success() {
        Card card1 = new Card();
        User user = new User();

        user.setId(1L);

        card1.setId(2L);
        card1.setUser(user);
        card1.setPassword("$2a$10$xJwL5v5zLz3K2.AJSQq.0eDqQ1Y9bGvK7XlWkUZJfN1cVbB2rYHdW");
        card1.setEmail("user@example.com");
        card1.setNumber("4276********1234");

        List<Card> expectedCards = List.of(card1);

        cardRepository = new CardRepository(entityManagerFactory);

        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(em);
        Mockito.when(em.getTransaction()).thenReturn(et);
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(Card.class)).thenReturn(cq);
        Mockito.when(cq.from(Card.class)).thenReturn(root);
        Mockito.when(cq.select(root)).thenReturn(cq);
        Mockito.when(em.createQuery(cq)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(expectedCards);

        List<Card> result = cardRepository.findAllCards();

        assertThat(result).isEqualTo(expectedCards);
    }



    @Test
    void findAllCards() {
        // Подготовка моков
        when(entityManagerFactory.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(et);
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Card.class)).thenReturn(cq);
        when(cq.from(Card.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(typedQuery);

        List<Card> expectedCards = Collections.singletonList(new Card());
        when(typedQuery.getResultList()).thenReturn(expectedCards);

        // Вызов метода
        List<Card> result = cardRepository.findAllCards();

        // Проверки
        assertNotNull(result);
        assertEquals(expectedCards.size(), result.size());
        verify(et).begin();
        verify(et).commit();
        verify(em).close();
    }
//
//    @Test
//    void findAllCards_QueryTimeoutException() {
//        when(entityManagerFactory.createEntityManager()).thenReturn(em);
//        when(em.getTransaction()).thenReturn(et);
//        when(em.getCriteriaBuilder()).thenReturn(cb);
//        when(cb.createQuery(Card.class)).thenReturn(cq);
//        when(cq.from(Card.class)).thenReturn(root);
//        when(em.createQuery(cq)).thenReturn(typedQuery);
//
//        // Эмулируем исключение
//        when(typedQuery.getResultList()).thenThrow(new QueryTimeoutException("DB timeout"));
//
//        // Проверяем, что исключение пробрасывается
//        assertThrows(QueryTimeoutException.class, () -> cardRepository.findAllCards());
//        verify(et).rollback();
//        verify(em).close();
//    }
//
//    @Test
//    void findAllCards_GenericException() {
//        when(entityManagerFactory.createEntityManager()).thenReturn(em);
//        when(em.getTransaction()).thenReturn(et);
//        when(em.getCriteriaBuilder()).thenReturn(cb);
//        when(cb.createQuery(Card.class)).thenReturn(cq);
//        when(cq.from(Card.class)).thenReturn(root);
//        when(em.createQuery(cq)).thenReturn(typedQuery);
//
//        // Эмулируем общее исключение
//        when(typedQuery.getResultList()).thenThrow(new RuntimeException("Generic error"));
//
//        // Проверяем, что исключение пробрасывается
//        assertThrows(RuntimeException.class, () -> cardRepository.findAllCards());
//        verify(et).rollback();
//        verify(em).close();
//    }
}

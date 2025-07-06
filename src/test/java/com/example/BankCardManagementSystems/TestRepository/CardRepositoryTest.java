package com.example.BankCardManagementSystems.TestRepository;

import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardRepositoryTest {

    @Mock
    private EntityManagerFactory entityManagerFactory;

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction et;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private CriteriaQuery<Card> cq;

    @Mock
    private Root<Card> root;

    @Mock
    private TypedQuery<Card> typedQuery;

    @InjectMocks
    private CardRepository cardRepository; // Класс, содержащий findAllCards()

    @Test
    void findAllCards_Success() {
        // 1. Подготовка тестовых данных
        Card card1 = new Card("411111******1111",
                "user1@example.com",
                "$2a$10$xJwL5v5Jz5UZJf5h5f5Z5.",
                new User());
        Card card2 = new Card("411123******1111",
                "user1@example.ru",
                "$2a$10$xJwL5v5Jz5UZJf5h5f5df.",
                new User());
        List<Card> expectedCards = Arrays.asList(card1, card2);

        // 2. Настройка моков
        when(entityManagerFactory.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(et);
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Card.class)).thenReturn(cq);
        when(cq.from(Card.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedCards);

        // 3. Вызов тестируемого метода
        List<Card> result = cardRepository.findAllCards();

        // 4. Проверки
        assertEquals(expectedCards.size(), result.size());
        verify(et).begin();
        verify(et).commit();
        verify(em).close();
    }



//    @Test
//    void findAllCards_Success() {
//        // Подготовка моков
//        when(entityManagerFactory.createEntityManager()).thenReturn(em);
//        when(em.getTransaction()).thenReturn(et);
//        when(em.getCriteriaBuilder()).thenReturn(cb);
//        when(cb.createQuery(Card.class)).thenReturn(cq);
//        when(cq.from(Card.class)).thenReturn(root);
//        when(em.createQuery(cq)).thenReturn(typedQuery);
//
//        List<Card> expectedCards = Collections.singletonList(new Card());
//        when(typedQuery.getResultList()).thenReturn(expectedCards);
//
//        // Вызов метода
//        List<Card> result = cardRepository.findAllCards();
//
//        // Проверки
//        assertNotNull(result);
//        assertEquals(expectedCards.size(), result.size());
//        verify(et).begin();
//        verify(et).commit();
//        verify(em).close();
//    }
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

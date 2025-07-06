package com.example.BankCardManagementSystems.service;

import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.enums.StatusCard;
import com.example.BankCardManagementSystems.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private static final Logger log = LoggerFactory.getLogger(CardService.class);

    @Autowired
    private CardRepository cardRepository;


















//    public List<Card> findAll(){
//        return cardRepository.findAll();
//    }
//    public Card save(Card card){
//        if(card == null){
//            throw new IllegalArgumentException("Card cannot be null");
//        }
//        log.debug("Attempting to save card {}", card);
//
//        try{
//            cardRepository.insertCard(card);
//            log.info("Card saved successfully with ID: {}", card.getId());
//            return card;
//        }catch (DataAccessException ex){
//            log.info("Failed to saved card with ID: {}", card.getId());
//            throw new DataAccessException("Failed to save card", ex) {};
//        }
//    }
//
//    public Card editCard(String cardId, StatusCard statusCard){
//        try{
//            log.debug("Attempting to save status card {}", cardId);
//
//            Card card = cardRepository.findById(cardId)
//                    .orElseThrow(() -> new EntityNotFoundException("Card not found"));
//
//            card.setStatus(statusCard.name());
//
//            cardRepository.updateStatusCard(card);
//            log.info("Card blocked successfully with ID: {}", cardId);
//            return card;
//        }catch (DataAccessException ex){
//            log.info("Failed to blocked card with ID: {}", cardId);
//            throw new DataAccessException("Failed updated status card", ex) {};
//        }catch (IllegalArgumentException ex){
//            throw new IllegalArgumentException("Card cannot be null", ex){};
//        }
//    }
//
//    public void deleteCard(String cardId) {
//        try{
//
//        }catch(IllegalArgumentException ex){
//            throw new IllegalArgumentException("Card cannot be null", ex){};
//        }
//    }
}

package com.example.BankCardManagementSystems.controller;

import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.entity.enums.StatusCard;
import com.example.BankCardManagementSystems.service.CardService;
import jakarta.persistence.PostUpdate;
import jakarta.websocket.server.PathParam;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cards/api")
public class CardController {

    @Autowired
    private CardService cardService;
























//    @GetMapping("/cards")
//    public ResponseEntity<List<Card>> findAll(){
//        return ResponseEntity.ok(cardService.findAll());
//    }
//
//    @PostMapping("/save/card")
//    public ResponseEntity<Card> saveCard(@RequestBody Card card,
//                                         @RequestParam Long userId) {
//        try {
//            card.setUserId(userId);
//            Card savedCard = cardService.save(card);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.PATCH, path = "/update/{cardId}/status")
//    public ResponseEntity<Card> editCard(@PathVariable String cardId,
//                                            @RequestParam StatusCard statusCard){
//        try{
//            Card newCard = cardService.editCard(cardId, statusCard);
//            return ResponseEntity.ok(newCard);
//        }catch(Exception ex){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DeleteMapping("/delete/{cardId}/card")
//    public ResponseEntity<Card> deleteCard(String cardId){
//        try{
//            cardService.deleteCard(cardId);
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }catch(Exception ex){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}

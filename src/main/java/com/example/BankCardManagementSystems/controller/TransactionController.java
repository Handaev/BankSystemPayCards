package com.example.BankCardManagementSystems.controller;


import com.example.BankCardManagementSystems.entity.Transaction;
import com.example.BankCardManagementSystems.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> findByAllTransaction(){
        return ResponseEntity.ok(transactionService.findAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable String id){
        return  ResponseEntity.ok(transactionService.findById(id));
    }

    @PostMapping
    public ResponseEntity.BodyBuilder save(@RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity.BodyBuilder update(@RequestBody Transaction transaction){
        transactionService.updateTransaction(transaction);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping
    public ResponseEntity.BodyBuilder delete(@RequestParam String id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.OK);
    }
}
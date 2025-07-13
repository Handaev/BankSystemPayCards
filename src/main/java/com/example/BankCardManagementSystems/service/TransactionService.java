package com.example.BankCardManagementSystems.service;


import com.example.BankCardManagementSystems.entity.Transaction;
import com.example.BankCardManagementSystems.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAllTransactions();
    }

    public Transaction findById(String id){
        return transactionRepository.findById(id);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.saveTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction){
        transactionRepository.updateTransaction(transaction);
    }

    public void deleteTransaction(String id){
        transactionRepository.deleteTransaction(id);
    }

}

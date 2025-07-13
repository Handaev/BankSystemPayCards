package com.example.BankCardManagementSystems.service;


import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.repository.UserRepository;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAllUsers();
    }

    public User findById(String id){
        return userRepository.findById(id);
    }

    public void save(User user){
        userRepository.saveUser(user);
    }

    public void update(User user){
        userRepository.updateUser(user);
    }

    public void delete(String id){
        userRepository.deleteUser(id);
    }
}

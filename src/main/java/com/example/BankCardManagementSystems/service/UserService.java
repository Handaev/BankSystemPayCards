package com.example.BankCardManagementSystems.service;


import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.repository.UserRepository;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;



















//    public User save(User user){
//        try{
//            if(user == null){
//                logger.debug("User cannot be null");
//            }
//
//            logger.info("Attempting to save user: {}", user);
//
//            userRepository.insertUser(user);
//            logger.info("User saved successfully with ID {}", user.getId());
//            return user;
//        }catch (DataAccessException ex){
//            logger.info("Failed to save user with ID: {}" + user.getId(), ex);
//            throw new DataAccessException("Failed to save card", ex) {};
//        }catch (IllegalArgumentException ex){
//            throw new IllegalArgumentException("User cannot be null", ex){};
//        }
//    }

}

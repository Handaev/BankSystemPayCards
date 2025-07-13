package com.example.BankCardManagementSystems.controller;


import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.Transaction;
import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> result = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        return  ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try{
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity.BodyBuilder update(@RequestBody User user){
        try{
            userService.update(user);
            return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder delete(@PathVariable String id){
        try{
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

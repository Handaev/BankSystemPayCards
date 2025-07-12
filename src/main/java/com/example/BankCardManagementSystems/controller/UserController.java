package com.example.BankCardManagementSystems.controller;


import com.example.BankCardManagementSystems.entity.Card;
import com.example.BankCardManagementSystems.entity.User;
import com.example.BankCardManagementSystems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("find/all/users")
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> result = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }















//    @PostMapping("/save/user")
//    public ResponseEntity<User> saveUser(@RequestBody User user){
//        try{
//            userService.save(user);
//            return ResponseEntity.status(HttpStatus.CREATED).body(user);
//        }catch (IllegalArgumentException ex){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }




}

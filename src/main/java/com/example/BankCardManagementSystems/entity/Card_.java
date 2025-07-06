//package com.example.BankCardManagementSystems.entity;
//
//import com.example.BankCardManagementSystems.entity.enums.StatusCard;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import jakarta.persistence.metamodel.StaticMetamodel;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.annotation.processing.Generated;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@StaticMetamodel(Card.class)
//@Generated("org.hibernate.processor.HibernateProcessor")
//public class Card_ {
//
//    private Long id;
//
//    private String number;
//
//    private String email;
//
//    private String password;
//
//    private Date expiryDate;
//
//    private String cvvEncrypted;
//
//    private String status = StatusCard.ACTIVE.name();
//
//    private String balance = "0";
//
//    private LocalDateTime createdAt;
//
//    private User user;
//}

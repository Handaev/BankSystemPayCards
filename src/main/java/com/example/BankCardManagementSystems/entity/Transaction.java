package com.example.BankCardManagementSystems.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_amount", nullable = false)
    private String transferAmount;

    @ManyToOne
    @JoinColumn(name = "card_from", nullable = false)
    @JsonBackReference
    private Card cardFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_to", nullable = false)
    @JsonBackReference
    private Card cardTo;

    @Column(name = "time_transfer", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}

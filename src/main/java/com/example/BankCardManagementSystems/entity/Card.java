package com.example.BankCardManagementSystems.entity;

import com.example.BankCardManagementSystems.entity.enums.StatusCard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_masked", nullable = false, unique = true)
    private String number;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "cvv_encrypted", nullable = false)
    private String cvvEncrypted;

    @Column(name = "status", nullable = false)
    private String status = StatusCard.ACTIVE.name();

    @Column(name = "balance")
    private String balance = "0";

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "cardFrom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactionFrom = new ArrayList<>();

    @OneToMany(mappedBy = "cardTo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactionTo = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Card(String number, String email, String password, User user){
        this.number = number;
        this.email = email;
        this.password = password;
        this.user = user;
    }

    @PrePersist
    private void expiryDatePlusSixYear() {
        if (this.expiryDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 6);
            this.expiryDate = cal.getTime();
        }
        hasCvvEncrypted();
    }

    private void hasCvvEncrypted() {
        if (this.cvvEncrypted == null) {
            this.cvvEncrypted = String.valueOf(
                    (long) (Math.random() * Math.pow(10, 3)));
        }
    }

}

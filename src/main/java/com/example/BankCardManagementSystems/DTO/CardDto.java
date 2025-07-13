package com.example.BankCardManagementSystems.DTO;

import lombok.Data;

@Data
public class CardDto {
    private Long id;

    private String number;

    private String email;

    private String status;

    private String balance;

}

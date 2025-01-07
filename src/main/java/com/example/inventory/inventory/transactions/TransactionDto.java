package com.example.inventory.inventory.transactions;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionDto {
    private  Long id;
    private Double total;
    private  Double revenue;
//    private Double service;
    private Long methodId;
    private Boolean isDebit;
}

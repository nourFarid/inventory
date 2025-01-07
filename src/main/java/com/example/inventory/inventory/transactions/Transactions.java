package com.example.inventory.inventory.transactions;

import com.example.inventory.inventory.transactionsMethods.TransactionsMethods;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private Double total;
private  Double revenue;
//private Double service;
private Boolean isDebit= false;
@CreationTimestamp
private LocalDate date;

@ManyToOne
    @JoinColumn(name = "methodId")
@JsonIgnoreProperties("transactions")

    private TransactionsMethods method;


public Transactions(TransactionDto dto){
    this.total= dto.getTotal();
    this.revenue= dto.getRevenue();
//    this.service= dto.getService();
//    this.isDebit = dto.getIsDebit();
    if(dto.getIsDebit()==false)
        this.isDebit= false;
    else
        this.isDebit= true;
    this.date= LocalDate.now();

    if (dto.getMethodId() != null) {
        this.method = TransactionsMethods.builder().id(dto.getMethodId()).build();
    } else {
        this.method = null;
    }

}}

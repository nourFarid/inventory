package com.example.inventory.inventory.transactionsMethods;

import com.example.inventory.inventory.transactions.Transactions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsMethods {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private String methodName;
@CreationTimestamp
private LocalDate date;
private  Double total;
    @OneToMany(mappedBy = "method", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Transactions> transactions;

public TransactionsMethods(String methodName, Double total){
    this.methodName = methodName;
    this.total = total;

}
}

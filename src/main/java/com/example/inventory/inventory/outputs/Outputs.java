package com.example.inventory.inventory.outputs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Outputs {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String output;
private  double value;
    @CreationTimestamp
    private LocalDate date;

}

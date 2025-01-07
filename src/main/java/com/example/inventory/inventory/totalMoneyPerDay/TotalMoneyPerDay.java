package com.example.inventory.inventory.totalMoneyPerDay;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TotalMoneyPerDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate date;
    private Double totalMoney;
    private  String source;

    public TotalMoneyPerDay(Double totalMoney, String source) {
        this.date = LocalDate.now();
this.source=source;
        this.totalMoney = totalMoney;
    }


}

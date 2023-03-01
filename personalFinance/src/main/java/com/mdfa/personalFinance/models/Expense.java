package com.mdfa.personalFinance.models;

import com.mdfa.personalFinance.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String purpose;
    private int amount;
    private LocalDate date;
    @Enumerated
    private Category category;

}

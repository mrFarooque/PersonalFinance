package com.mdfa.personalFinance.models;

import com.mdfa.personalFinance.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String purpose;
    private int amount;
    private LocalDate date;
    @Enumerated
    private Category category;
}

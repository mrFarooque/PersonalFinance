package com.mdfa.personalFinance.models;

import com.mdfa.personalFinance.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private LocalDateTime date;
    @Enumerated
    private Category category;

}

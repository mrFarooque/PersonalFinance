package com.mdfa.personalFinance.models;

import com.mdfa.personalFinance.enums.Category;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String purpose;
    private int amount;
    private LocalDateTime date;
    @Enumerated
    private Category category;
}

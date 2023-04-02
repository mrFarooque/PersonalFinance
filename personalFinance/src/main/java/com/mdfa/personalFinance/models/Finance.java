package com.mdfa.personalFinance.models;

import com.mdfa.personalFinance.enums.Category;
import com.mdfa.personalFinance.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String purpose;
    private int amount;
    private LocalDate date;
    @Enumerated
    private Category category;
    private Type type;
    @ManyToOne
    private User user;
}

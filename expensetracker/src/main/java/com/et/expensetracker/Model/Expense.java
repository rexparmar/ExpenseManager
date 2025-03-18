package com.et.expensetracker.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amount;
    private String description;
    private String category;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

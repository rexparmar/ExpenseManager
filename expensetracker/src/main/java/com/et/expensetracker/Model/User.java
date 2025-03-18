package com.et.expensetracker.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private int balance;



    @OneToMany
    @JoinColumn(name="expenses")
    private List<Expense> expenses;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getUsername(){
        return username;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }

    public void setBalance(int balance){
        this.balance=balance;
    }

    public int getBalance(){
        return balance;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}



package com.et.expensetracker.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amount;
    private String type;
    private String paymentMethod;
    private String status;
    private String description;
    private LocalDate date;
    private String payee;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(Long id){
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod=paymentMethod;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

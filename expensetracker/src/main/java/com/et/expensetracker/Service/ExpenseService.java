package com.et.expensetracker.Service;


import com.et.expensetracker.Model.Expense;
import com.et.expensetracker.Model.User;
import com.et.expensetracker.Repository.ExpenseRepository;
import com.et.expensetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository repo;
    @Autowired
    private UserRepository userRepository;

    public void addExpense(Expense expense, Long userId) throws Exception{
        User user =userRepository.getById(userId);
        if(expense.getType().equals("Income")){
            user.setBalance(expense.getAmount()+user.getBalance());
            expense.setDate(LocalDate.now());
            expense.setUser(user);
            repo.save(expense);
            userRepository.save(user);
        }else if(expense.getType().equals("Expense")&&expense.getAmount()<user.getBalance()){
            user.setBalance(user.getBalance()-expense.getAmount());
            expense.setDate(LocalDate.now());
            expense.setUser(user);
            repo.save(expense);
            userRepository.save(user);
        }else{
            throw new Exception("You can not add expense larger than your current account balance!");
        }
    }

    public List<Expense> getExpensesByUserId(Long userId) throws Exception{
        return repo.findAllByUserId(userId);
    }

    public Expense updateExpense(Long id ,Expense expense){
        Expense existingExpense = repo.getById(id);
        existingExpense.setDate(LocalDate.now());
        existingExpense.setAmount(existingExpense.getAmount());
        existingExpense.setPayee(expense.getPayee());
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setId(existingExpense.getId());
        existingExpense.setUser(existingExpense.getUser());
        existingExpense.setDate(LocalDate.now());
        return repo.save(existingExpense);
    }

    public Optional<Expense> getExpenseById(Long id){
        return repo.findById(id);
    }
}

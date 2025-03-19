package com.et.expensetracker.Controller;

import com.et.expensetracker.Model.Expense;
import com.et.expensetracker.Model.User;
import com.et.expensetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    private ExpenseService service;

    @PostMapping("/{userId}/expense")
    public ResponseEntity<?> addExpense(@PathVariable Long userId, @RequestBody Expense expense) throws Exception {
        service.addExpense(expense,userId);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/{userId}/expense")
    public ResponseEntity<?> getExpenseByUserId(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(service.getExpensesByUserId(userId));
    }

    @PostMapping("{expenseId}/update")
    public ResponseEntity<?> updateExpense(@PathVariable Long expenseId, @RequestBody Expense expense){
        service.updateExpense(expenseId,expense);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/{id}/getExpense")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
        if(service.getExpenseById(id).isPresent()){
        return ResponseEntity.ok(service.getExpenseById(id));
        }else{
            return ResponseEntity.ok("No Expense found by this id!!");
        }
    }

}

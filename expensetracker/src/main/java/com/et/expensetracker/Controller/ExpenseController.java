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
    public ResponseEntity<?> getExpenseById(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(service.getExpensesByUserId(userId));
    }
}

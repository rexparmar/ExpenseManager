package com.et.expensetracker.Repository;

import com.et.expensetracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findAllByUserId(Long userId);
}

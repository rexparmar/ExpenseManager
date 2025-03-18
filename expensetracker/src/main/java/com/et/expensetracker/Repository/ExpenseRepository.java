package com.et.expensetracker.Repository;

import com.et.expensetracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}

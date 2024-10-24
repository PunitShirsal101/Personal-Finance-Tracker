package com.spunit.controller;

import com.spunit.model.Transaction;
import com.spunit.model.User;
import com.spunit.service.TransactionService;
import com.spunit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Transaction> getAllTransactions(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return transactionService.getTransactions(user);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        transaction.setUser(user);
        return transactionService.saveTransaction(transaction);
    }
}

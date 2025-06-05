package com.meu.capital.api.controller;

import com.meu.capital.api.dto.TransactionDTO;
import com.meu.capital.api.dto.TransactionDetailsDTO;
import com.meu.capital.api.dto.TransactionsUpdateDTO;
import com.meu.capital.api.model.TransactionType;
import com.meu.capital.api.service.user.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDetailsDTO> transaction(@RequestBody @Valid TransactionDTO data) {
        var dto = transactionService.transaction(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> transactionDelete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDetailsDTO>> listTransactions(@PageableDefault(size = 10, sort = "amount", direction = Sort.Direction.DESC) Pageable pageable) {
        var transactions = transactionService.listTransactions(pageable);

        return ResponseEntity.ok(transactions);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<TransactionDetailsDTO> updateTransaction(@RequestBody @Valid TransactionsUpdateDTO data) {
        var transactions = transactionService.updateTransactionById(data);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/incomes")
    public ResponseEntity<BigDecimal> totalIncomeTransactions() {
        var sum = transactionService.sumAllIncomeTransaction();
        return ResponseEntity.ok(sum);
    }

    @GetMapping("/expenses")
    public ResponseEntity<BigDecimal> totalExpenseTransactions() {
        var sum = transactionService.sumAllExpenseTransaction();
        return ResponseEntity.ok(sum);
    }
}

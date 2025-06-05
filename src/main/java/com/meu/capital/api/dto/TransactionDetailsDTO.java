package com.meu.capital.api.dto;

import com.meu.capital.api.model.Transaction;
import com.meu.capital.api.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetailsDTO(Long id, Long idUser, TransactionType type, LocalDate date, String description, BigDecimal amount) {
    public TransactionDetailsDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getUser().getId(), transaction.getType(), transaction.getDate(), transaction.getDescription(), transaction.getAmount());
    }
}

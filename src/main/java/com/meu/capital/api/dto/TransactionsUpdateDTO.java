package com.meu.capital.api.dto;

import com.meu.capital.api.model.TransactionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionsUpdateDTO (
        @NotNull
        Long id_transaction,
        TransactionType type,
        LocalDate date,
        String description,
        BigDecimal amount
){
}

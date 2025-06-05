package com.meu.capital.api.repository;

import com.meu.capital.api.model.Transaction;
import com.meu.capital.api.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("""
                SELECT SUM(s.amount) FROM Transaction s
                WHERE s.type = :type
            """)
    BigDecimal sumAmountByType(@Param("type") TransactionType type);
}

package com.meu.capital.api.service.user;

import com.meu.capital.api.dto.TransactionDTO;
import com.meu.capital.api.dto.TransactionDetailsDTO;
import com.meu.capital.api.dto.TransactionsUpdateDTO;
import com.meu.capital.api.model.Transaction;
import com.meu.capital.api.model.TransactionType;
import com.meu.capital.api.repository.TransactionRepository;
import com.meu.capital.api.repository.UserRepository;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TransactionDetailsDTO transaction(TransactionDTO transactionDTO) {
        var user = userRepository.findById(transactionDTO.id_user()).get();
        System.out.println(user);
        var type = transactionDTO.type();
        var amount = transactionDTO.amount();
        var date = transactionDTO.date();
        var description = transactionDTO.description();

        var transaction = new Transaction(null, user, type, date, description, amount);
        transactionRepository.save(transaction);

        if (transactionDTO.type() == TransactionType.INCOME){
            var balance = user.getBalance().add(amount);
            userRepository.updateUserBalance(user.getId(), balance);
        }
        if (transactionDTO.type() == TransactionType.EXPENSE){
            var balance = user.getBalance().subtract(amount);
            userRepository.updateUserBalance(user.getId(), balance);
        }
        System.out.println(transactionDTO);
        return new TransactionDetailsDTO(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Page<TransactionDetailsDTO> listTransactions(@PageableDefault(size = 10, sort = "amount", direction = Sort.Direction.DESC)Pageable pageable) {
        var page = transactionRepository.findAll(pageable).map(TransactionDetailsDTO::new);

        return page;
    }

    public TransactionDetailsDTO updateTransactionById (TransactionsUpdateDTO transactionsUpdateDTO) {
        var optionalTransaction = transactionRepository.findById(transactionsUpdateDTO.id_transaction());
        if (optionalTransaction.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }

        var transaction = optionalTransaction.get();

        if (transactionsUpdateDTO.type() != null) {
            transaction.setType(transactionsUpdateDTO.type());
        }

        if (transactionsUpdateDTO.date() != null) {
            transaction.setDate(transactionsUpdateDTO.date());
        }

        if (transactionsUpdateDTO.description() != null) {
            transaction.setDescription(transactionsUpdateDTO.description());
        }

        if (transactionsUpdateDTO.amount() != null) {
            transaction.setAmount(transactionsUpdateDTO.amount());
        }

        transactionRepository.save(transaction);

        return new TransactionDetailsDTO(transaction);
    }

    public BigDecimal sumAllIncomeTransaction() {
        return transactionRepository.sumAmountByType(TransactionType.INCOME);
    }

    public BigDecimal sumAllExpenseTransaction() {
        return transactionRepository.sumAmountByType(TransactionType.EXPENSE);
    }


}

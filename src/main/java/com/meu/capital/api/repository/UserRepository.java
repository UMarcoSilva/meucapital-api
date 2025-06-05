package com.meu.capital.api.repository;

import com.meu.capital.api.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);

    @Transactional
    @Modifying
    @Query("""
                UPDATE User u
                SET u.balance = :balance
                WHERE u.id = :id
            """)
    int updateUserBalance(@Param("id") Long id, @Param("balance") BigDecimal balance);
}

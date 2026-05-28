package com.sahil.trackwallet.repositories;

import com.sahil.trackwallet.entity.Transaction;
import com.sahil.trackwallet.entity.User;
import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserAndCategory(
            User user,
            String category
    );

    List<Transaction> findByUserAndType(
            User user,
            TransactionType type
    );

    List<Transaction> findByUserAndCreatedAtBetween(
            User user,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    @Query("""
       SELECT SUM(t.amount)
       FROM Transaction t
       WHERE t.user = :user
       AND t.type = :type
       """)
    Double getTotalAmountByType(
            @Param("user") User user,
            @Param("type") TransactionType type
    );


    List<Transaction> findByUserAndCategory(
            User user,
            Category category
    );


    Page<Transaction> findByUser(
            User user,
            Pageable pageable
    );

}
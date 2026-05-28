package com.sahil.trackwallet.services;

import com.sahil.trackwallet.dtos.dashboard.SummaryResponseDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionRequestDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionResponseDTO;
import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponseDTO createTransaction(
            TransactionRequestDTO request
    );

    List<TransactionResponseDTO> getMyTransactions();

    TransactionResponseDTO updateTransaction(
            Long transactionId,
            TransactionRequestDTO request
    );

    void deleteTransaction(Long transactionId);


    SummaryResponseDTO getSummary();

    List<TransactionResponseDTO> getTransactionsByCategory(
            Category category
    );


    List<TransactionResponseDTO> getTransactionsByType(
            TransactionType type
    );

    List<TransactionResponseDTO> getTransactionsByDateRange(
            LocalDate startDate,
            LocalDate endDate
    );


    Page<TransactionResponseDTO> getTransactionsWithPagination(
            int page,
            int size,
            String sortBy
    );

}
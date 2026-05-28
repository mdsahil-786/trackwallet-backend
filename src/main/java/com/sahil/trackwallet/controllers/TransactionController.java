package com.sahil.trackwallet.controllers;

import com.sahil.trackwallet.dtos.dashboard.SummaryResponseDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionRequestDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionResponseDTO;
import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import com.sahil.trackwallet.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponseDTO createTransaction(
            @Valid @RequestBody TransactionRequestDTO request
    ) {

        return transactionService.createTransaction(request);
    }

    @GetMapping
    public List<TransactionResponseDTO> getMyTransactions() {

        return transactionService.getMyTransactions();
    }

    @PutMapping("/{transactionId}")
    public TransactionResponseDTO updateTransaction(
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionRequestDTO request
    ) {

        return transactionService.updateTransaction(
                transactionId,
                request
        );
    }

    @DeleteMapping("/{transactionId}")
    public String deleteTransaction(
            @PathVariable Long transactionId
    ) {

        transactionService.deleteTransaction(transactionId);

        return "Transaction deleted successfully";
    }


    @GetMapping("/summary")
    public SummaryResponseDTO getSummary() {

        return transactionService.getSummary();
    }

    @GetMapping("/category/{category}")
    public List<TransactionResponseDTO> getTransactionsByCategory(
            @PathVariable Category category
    ) {

        return transactionService
                .getTransactionsByCategory(category);
    }
    @GetMapping("/type/{type}")
    public List<TransactionResponseDTO> getTransactionsByType(
            @PathVariable TransactionType type
    ) {

        return transactionService
                .getTransactionsByType(type);
    }

    @GetMapping("/date-range")
    public List<TransactionResponseDTO> getTransactionsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {

        return transactionService.getTransactionsByDateRange(
                startDate,
                endDate
        );
    }


    @GetMapping("/paged")
    public Page<TransactionResponseDTO> getTransactionsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {

        return transactionService.getTransactionsWithPagination(
                page,
                size,
                sortBy
        );
    }

}
package com.sahil.trackwallet.services;

import com.sahil.trackwallet.dtos.dashboard.SummaryResponseDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionRequestDTO;
import com.sahil.trackwallet.dtos.transaction.TransactionResponseDTO;
import com.sahil.trackwallet.entity.Transaction;
import com.sahil.trackwallet.entity.User;
import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import com.sahil.trackwallet.mappers.TransactionMapper;
import com.sahil.trackwallet.repositories.TransactionRepository;
import com.sahil.trackwallet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl
        implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    @Override
    public TransactionResponseDTO createTransaction(
            TransactionRequestDTO request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Transaction transaction = Transaction.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .type(request.getType())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return TransactionMapper.toDTO(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> getMyTransactions() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        List<Transaction> transactions =
                transactionRepository.findByUser(user);

        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public TransactionResponseDTO updateTransaction(
            Long transactionId,
            TransactionRequestDTO request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Transaction transaction =
                transactionRepository.findById(transactionId)
                        .orElseThrow(() ->
                                new RuntimeException("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized access");
        }

        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
        transaction.setType(request.getType());

        Transaction updatedTransaction =
                transactionRepository.save(transaction);

        return TransactionMapper.toDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Transaction transaction =
                transactionRepository.findById(transactionId)
                        .orElseThrow(() ->
                                new RuntimeException("Transaction not found"));

        if (!transaction.getUser().getId().equals(user.getId())) {

            throw new RuntimeException("Unauthorized access");
        }

        transactionRepository.delete(transaction);
    }


    @Override
    public SummaryResponseDTO getSummary() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Double totalIncome =
                transactionRepository.getTotalAmountByType(
                        user,
                        TransactionType.INCOME
                );

        Double totalExpense =
                transactionRepository.getTotalAmountByType(
                        user,
                        TransactionType.EXPENSE
                );

        if (totalIncome == null) {
            totalIncome = 0.0;
        }

        if (totalExpense == null) {
            totalExpense = 0.0;
        }

        Double balance = totalIncome - totalExpense;

        return SummaryResponseDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(balance)
                .build();
    }
    @Override
    public List<TransactionResponseDTO> getTransactionsByCategory(
            Category category
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        List<Transaction> transactions =
                transactionRepository.findByUserAndCategory(
                        user,
                        category
                );

        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .toList();
    }



    @Override
    public List<TransactionResponseDTO> getTransactionsByType(
            TransactionType type
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        List<Transaction> transactions =
                transactionRepository.findByUserAndType(
                        user,
                        type
                );

        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .toList();
    }


    @Override
    public List<TransactionResponseDTO> getTransactionsByDateRange(
            LocalDate startDate,
            LocalDate endDate
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        LocalDateTime start =
                startDate.atStartOfDay();

        LocalDateTime end =
                endDate.atTime(23, 59, 59);

        List<Transaction> transactions =
                transactionRepository
                        .findByUserAndCreatedAtBetween(
                                user,
                                start,
                                end
                        );

        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .toList();
    }





    @Override
    public Page<TransactionResponseDTO> getTransactionsWithPagination(
            int page,
            int size,
            String sortBy
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException("User not found"));

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy).descending()
                );

        Page<Transaction> transactions =
                transactionRepository.findByUser(
                        user,
                        pageable
                );

        return transactions.map(
                TransactionMapper::toDTO
        );
    }
}
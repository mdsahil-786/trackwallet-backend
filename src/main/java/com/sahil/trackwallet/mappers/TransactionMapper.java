package com.sahil.trackwallet.mappers;

import com.sahil.trackwallet.dtos.transaction.TransactionResponseDTO;
import com.sahil.trackwallet.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponseDTO toDTO(
            Transaction transaction
    ) {

        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .title(transaction.getTitle())
                .amount(transaction.getAmount())
                .category(transaction.getCategory())
                .type(transaction.getType())
                .createdAt(transaction.getCreatedAt())
                .userName(transaction.getUser().getName())
                .userEmail(transaction.getUser().getEmail())
                .build();
    }
}
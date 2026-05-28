package com.sahil.trackwallet.dto.transaction;

import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long id;

    private String title;

    private Double amount;

    private Category category;

    private TransactionType type;

    private LocalDateTime createdAt;

    private String userName;

    private String userEmail;
}
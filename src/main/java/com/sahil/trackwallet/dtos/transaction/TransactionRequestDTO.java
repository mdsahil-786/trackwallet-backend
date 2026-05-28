package com.sahil.trackwallet.dto.transaction;

import com.sahil.trackwallet.enums.Category;
import com.sahil.trackwallet.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;
}
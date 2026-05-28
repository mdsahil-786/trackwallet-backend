package com.sahil.trackwallet.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponseDTO {

    private Double totalIncome;

    private Double totalExpense;

    private Double balance;
}
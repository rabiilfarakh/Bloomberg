package com.example._bloomberg_.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionResDTO(
        String transactionId,
        @NotNull String currencyFrom,
        @NotNull String currencyTo,
        @NotNull String timestamp,
        @NotNull @Positive BigDecimal amount
) {

}
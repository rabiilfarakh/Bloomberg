package com.example._bloomberg_.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record TransactionReqDTO(

        @NotBlank(message = "Transaction ID is mandatory")
        String transactionId,

        @NotBlank(message = "Currency From is mandatory")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency From must be ISO 4217 format")
        String currencyFrom,

        @NotBlank(message = "Currency To is mandatory")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency To must be ISO 4217 format")
        String currencyTo,

        @NotBlank(message = "Timestamp is mandatory")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?Z$",
                message = "Timestamp must be in ISO-8601 format (e.g., 2024-08-13T12:30:00Z)"
        )
        String timestamp,

        @NotBlank(message = "Amount is mandatory")
        @Pattern(
                regexp = "^\\d+(\\.\\d{1,2})?$",
                message = "Amount must be a valid decimal (e.g., 1000.50)"
        )
        BigDecimal amount

) {}

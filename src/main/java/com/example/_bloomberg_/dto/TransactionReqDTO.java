package com.example._bloomberg_.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TransactionReqDTO(

        @NotBlank(message = "Transaction ID is mandatory")
        String transactionId,

        @NotBlank(message = "Currency From is mandatory")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency From must be ISO 4217 format")
        String currencyFrom,

        @NotBlank(message = "Currency To is mandatory")
        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency To must be ISO 4217 format")
        String currencyTo,

        @NotNull(message = "Timestamp is mandatory")
        @PastOrPresent(message = "Timestamp must be in the past or present")
        ZonedDateTime timestamp,

        @NotNull(message = "Amount is mandatory")
        @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Amount must be a valid decimal with up to 2 decimals")
        BigDecimal amount

) {}

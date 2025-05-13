package com.example._bloomberg_.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @NotBlank(message = "Transaction ID is mandatory")
    private String transactionId;

    @NotBlank(message = "Currency From is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency From must be ISO 4217 format")
    private String currencyFrom;

    @NotBlank(message = "Currency To is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency To must be ISO 4217 format")
    private String currencyTo;

    @NotNull(message = "Timestamp is mandatory")
    private LocalDateTime timestamp;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private BigDecimal amount;
}

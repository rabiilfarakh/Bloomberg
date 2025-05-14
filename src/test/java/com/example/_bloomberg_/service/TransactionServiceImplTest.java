package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.entity.Transaction;
import com.example._bloomberg_.mapper.TransactionMapper;
import com.example._bloomberg_.repository.TransactionRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    private Validator validator;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionReqDTO validTransactionReqDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        transactionService = new TransactionServiceImpl(transactionRepository, transactionMapper, validator);

        validTransactionReqDTO = new TransactionReqDTO(
                "a1b2c3d4",
                "USD",
                "EUR",
                ZonedDateTime.now(),
                new BigDecimal("100.00")
        );
    }

    @Test
    void testImportTransactions_Success() {
        Transaction transaction = new Transaction();
        when(transactionMapper.toEntity(validTransactionReqDTO)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        transactionService.importTransactions(List.of(validTransactionReqDTO));

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void testImportTransactions_ValidationException_InvalidCurrency() {
        TransactionReqDTO invalidDTO = new TransactionReqDTO(
                "a1b2c3d4",
                "US",
                "EUR",
                ZonedDateTime.now(),
                new BigDecimal("100.00")
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transactionService.importTransactions(List.of(invalidDTO))
        );

        assertTrue(exception.getMessage().contains("Validation failed"));
    }

    @Test
    void testImportTransactions_ValidationException_NullAmount() {
        TransactionReqDTO invalidDTO = new TransactionReqDTO(
                "a1b2c3d4",
                "USD",
                "EUR",
                ZonedDateTime.now(),
                null
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> transactionService.importTransactions(List.of(invalidDTO))
        );

        assertTrue(exception.getMessage().contains("Validation failed"));
    }

}
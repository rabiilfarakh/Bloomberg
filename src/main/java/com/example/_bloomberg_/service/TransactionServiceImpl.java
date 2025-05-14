package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.dto.TransactionResDTO;
import com.example._bloomberg_.entity.Transaction;
import com.example._bloomberg_.mapper.TransactionMapper;
import com.example._bloomberg_.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final Validator validator;

    @Override
    public void importTransactions(List<TransactionReqDTO> transactionReqDTOS) {
        for (TransactionReqDTO dto : transactionReqDTOS) {
            Set<ConstraintViolation<TransactionReqDTO>> violations = validator.validate(dto);
            if (!violations.isEmpty()) {
                throw new IllegalArgumentException("Validation failed for transaction: " + violations);
            }

            Transaction transaction = transactionMapper.toEntity(dto);
            transactionRepository.save(transaction);
        }
    }

    @Override
    public List<TransactionResDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}


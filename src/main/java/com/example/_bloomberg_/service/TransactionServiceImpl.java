package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.entity.Transaction;
import com.example._bloomberg_.mapper.TransactionMapper;
import com.example._bloomberg_.repository.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public void importTransactions(List<TransactionReqDTO> transactions) {
        for (TransactionReqDTO transactionReqDTO : transactions) {
            try {
                Transaction transaction = transactionMapper.toEntity(transactionReqDTO);

                if (!transactionRepository.existsByTransactionId(transactionReqDTO.transactionId())) {
                    transactionRepository.save(transaction);
                }
            } catch (ConstraintViolationException e) {
                throw new ValidationException("Invalid data: " + e.getMessage());
            } catch (Exception e) {
                throw new ValidationException("Error processing transaction: " + e.getMessage());
            }
        }
    }
}

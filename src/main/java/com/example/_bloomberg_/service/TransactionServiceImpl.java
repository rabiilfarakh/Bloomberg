package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.entity.Transaction;
import com.example._bloomberg_.mapper.TransactionMapper;
import com.example._bloomberg_.repository.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public void importTransactions(List<TransactionReqDTO> transactionReqDTOList) {
        logger.info("Starting import of {} transactions", transactionReqDTOList.size());

        for (TransactionReqDTO transactionReqDTO : transactionReqDTOList) {
            try {
                logger.debug("Processing transaction ID: {}", transactionReqDTO.transactionId());

                Transaction transaction = transactionMapper.toEntity(transactionReqDTO);

                if (!transactionRepository.existsByTransactionId(transactionReqDTO.transactionId())) {
                    transactionRepository.save(transaction);
                    logger.info("Transaction {} saved successfully", transaction.getTransactionId());
                } else {
                    logger.warn("Transaction {} already exists. Skipping...", transactionReqDTO.transactionId());
                }

            } catch (ConstraintViolationException e) {
                logger.error("Validation error for transaction {}: {}", transactionReqDTO.transactionId(), e.getMessage());
                throw new ValidationException("Invalid data: " + e.getMessage());
            } catch (Exception e) {
                logger.error("Unexpected error while processing transaction {}: {}", transactionReqDTO.transactionId(), e.getMessage());
                throw new ValidationException("Error processing transaction: " + e.getMessage());
            }
        }

        logger.info("Import of transactions completed.");
    }
}

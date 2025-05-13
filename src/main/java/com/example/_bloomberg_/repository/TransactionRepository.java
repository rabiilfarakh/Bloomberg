package com.example._bloomberg_.repository;

import com.example._bloomberg_.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    boolean existsByTransactionId(String transactionId);
}

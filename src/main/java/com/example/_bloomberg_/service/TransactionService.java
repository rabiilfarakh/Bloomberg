package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.dto.TransactionResDTO;

import java.util.List;

public interface TransactionService {
    void importTransactions(List<TransactionReqDTO> transactions);
    List<TransactionResDTO> getAllTransactions();
}
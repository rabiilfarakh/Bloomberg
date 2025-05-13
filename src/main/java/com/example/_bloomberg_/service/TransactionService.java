package com.example._bloomberg_.service;

import com.example._bloomberg_.dto.TransactionReqDTO;

import java.util.List;

public interface TransactionService {
    void importTransactions(List<TransactionReqDTO> transactions);
}
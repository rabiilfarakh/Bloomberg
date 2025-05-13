package com.example._bloomberg_.controller;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importTransactions(@RequestBody @Valid List<TransactionReqDTO> transactions) {
        transactionService.importTransactions(transactions);
        return ResponseEntity.ok("Transactions imported successfully");
    }

}


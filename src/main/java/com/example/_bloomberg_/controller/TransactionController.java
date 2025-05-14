package com.example._bloomberg_.controller;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.dto.TransactionResDTO;
import com.example._bloomberg_.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<TransactionResDTO>> getAllTransactions() {
        List<TransactionResDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

}


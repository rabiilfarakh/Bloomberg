package com.example._bloomberg_.mapper;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.dto.TransactionResDTO;
import com.example._bloomberg_.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionReqDTO transactionReqDTO);
    TransactionResDTO toDTO(Transaction transaction);
}

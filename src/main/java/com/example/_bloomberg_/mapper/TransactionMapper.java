package com.example._bloomberg_.mapper;

import com.example._bloomberg_.dto.TransactionReqDTO;
import com.example._bloomberg_.dto.TransactionResDTO;
import com.example._bloomberg_.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "timestamp", source = "timestamp")
    Transaction toEntity(TransactionReqDTO transactionReqDTO);
    TransactionResDTO toDTO(Transaction transaction);
}

package com.bank.repository;

import com.bank.model.TransactionWithFee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionWithFee,Integer> {

}

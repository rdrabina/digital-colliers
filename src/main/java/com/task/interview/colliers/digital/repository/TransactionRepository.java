package com.task.interview.colliers.digital.repository;

import com.task.interview.colliers.digital.document.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Long> {
}

package com.task.interview.colliers.digital.repository;

import com.task.interview.colliers.digital.document.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Long> {

    List<Transaction> findAllByAccountTypeIdIn(List<Long> accountTypeIds);

    List<Transaction> findAllByCustomerIdIn(List<Long> customerIds);

    List<Transaction> findAllByAccountTypeIdInAndCustomerIdIn(List<Long> accountTypeIds, List<Long> customerIds);

}

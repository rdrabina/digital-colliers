package com.task.interview.colliers.digital.repository

import com.task.interview.colliers.digital.document.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : MongoRepository<Transaction?, Long?> {

    fun findAllByAccountTypeIdIn(accountTypeIds: List<Long?>?): List<Transaction?>?

    fun findAllByCustomerIdIn(customerIds: List<Long?>?): List<Transaction?>?

    fun findAllByAccountTypeIdInAndCustomerIdIn(accountTypeIds: List<Long?>?, customerIds: List<Long?>?): List<Transaction?>?

}
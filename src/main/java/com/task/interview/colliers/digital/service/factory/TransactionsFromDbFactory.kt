package com.task.interview.colliers.digital.service.factory

import com.task.interview.colliers.digital.document.Transaction
import com.task.interview.colliers.digital.repository.TransactionRepository
import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionsFromDbFactory @Autowired constructor(private val transactionRepository: TransactionRepository) {

    fun resolve(accountTypeIds: List<Long?>?, customerIds: List<Long?>?): List<Transaction?>? {
        if (ObjectUtils.allNotNull(accountTypeIds, customerIds)) {
            return transactionRepository.findAllByAccountTypeIdInAndCustomerIdIn(accountTypeIds, customerIds)
        }
        if (Objects.nonNull(accountTypeIds)) {
            return transactionRepository.findAllByAccountTypeIdIn(accountTypeIds)
        }

        return if (Objects.nonNull(customerIds)) {
            transactionRepository.findAllByCustomerIdIn(customerIds)
        } else transactionRepository.findAll()
    }

}
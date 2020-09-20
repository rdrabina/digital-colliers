package com.task.interview.colliers.digital.service

import com.task.interview.colliers.digital.dto.TransactionDto

interface TransactionService {

    fun getTransactions(accountType: String?, customerId: String?): List<TransactionDto?>?

}
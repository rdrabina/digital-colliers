package com.task.interview.colliers.digital.provider.factory

import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType
import com.task.interview.colliers.digital.document.AccountType
import com.task.interview.colliers.digital.document.BankDocument
import com.task.interview.colliers.digital.document.Customer
import com.task.interview.colliers.digital.document.Transaction
import com.task.interview.colliers.digital.exception.DocumentMappingFunctionNotFoundException
import java.util.*
import java.util.function.Function

object DocumentTypeFactory {

    private var DOCUMENT_MAPPING_FUNCTIONS: Map<DocumentInitType, Function<Array<String>, BankDocument>>? = null

    fun resolve(documentInitType: DocumentInitType): Function<Array<String>, BankDocument> {
        return Optional.ofNullable(DOCUMENT_MAPPING_FUNCTIONS!![documentInitType])
                .orElseThrow { DocumentMappingFunctionNotFoundException() }
    }

    init {
        DOCUMENT_MAPPING_FUNCTIONS = mapOf(
                DocumentInitType.ACCOUNT_TYPE to Function { fields: Array<String>? -> AccountType.mapToAccountType(fields) },
                DocumentInitType.CUSTOMER to Function { fields: Array<String>? -> Customer.mapToCustomer(fields) },
                DocumentInitType.TRANSACTION to Function { fields: Array<String>? -> Transaction.mapToTransaction(fields) }
        )
    }
}
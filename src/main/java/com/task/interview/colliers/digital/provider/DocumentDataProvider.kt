package com.task.interview.colliers.digital.provider

import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType
import com.task.interview.colliers.digital.document.BankDocument
import com.task.interview.colliers.digital.parser.CsvDataParser.readDataFromFile
import com.task.interview.colliers.digital.provider.factory.DocumentTypeFactory.resolve

object DocumentDataProvider {

    private val DATA_DOCUMENTS: MutableMap<String, Map<Long, BankDocument>> = HashMap()

    @JvmStatic
    fun getBankDocumentData(filePath: String, documentInitType: DocumentInitType): Map<Long, BankDocument> {
        if (!DATA_DOCUMENTS.containsKey(filePath)) {
            val bankDocumentData = createBankDocumentData(filePath, documentInitType)
            DATA_DOCUMENTS[filePath] = bankDocumentData
            return bankDocumentData
        }
        return DATA_DOCUMENTS[filePath]!!
    }

    private fun createBankDocumentData(filePath: String, documentInitType: DocumentInitType): Map<Long, BankDocument> {
        return readDataFromFile(filePath)
                .map { lines -> resolve(documentInitType).apply(lines) }
                .associateBy { it.id }
    }
}
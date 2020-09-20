package com.task.interview.colliers.digital.provider;

import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType;
import com.task.interview.colliers.digital.document.BankDocument;
import com.task.interview.colliers.digital.parser.CsvDataParser;
import com.task.interview.colliers.digital.provider.factory.DocumentTypeFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DocumentDataProvider {

    private static final Map<String, Map<Long, BankDocument>> DATA_DOCUMENTS = new HashMap<>();

    public static Map<Long, BankDocument> getBankDocumentData(String filePath, DocumentInitType documentInitType) {
        if (!DATA_DOCUMENTS.containsKey(filePath)) {
            Map<Long, BankDocument> bankDocumentData = createBankDocumentData(filePath, documentInitType);
            DATA_DOCUMENTS.put(filePath, bankDocumentData);
            return bankDocumentData;
        }

        return DATA_DOCUMENTS.get(filePath);
    }

    private static Map<Long, BankDocument> createBankDocumentData(String filePath, DocumentInitType documentInitType) {
        return CsvDataParser.readDataFromFile(filePath)
                .stream()
                .map(DocumentTypeFactory.resolve(documentInitType))
                .collect(
                        Collectors.toMap(
                                BankDocument::getId, Function.identity(), (obj1, obj2) -> obj1
                        )
                );
    }
}

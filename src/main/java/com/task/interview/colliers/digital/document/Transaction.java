package com.task.interview.colliers.digital.document;

import com.task.interview.colliers.digital.exception.InvalidNumberOfFieldsException;
import com.task.interview.colliers.digital.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static com.task.interview.colliers.digital.document.constant.Constants.TRANSACTION_DATE_FORMAT;
import static com.task.interview.colliers.digital.document.constant.Constants.TRANSACTION_FIELDS_NUMBER;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BankDocument {

    private static final String COMMA_SEPARATOR = ",";
    private static final String DOT_SEPARATOR = ".";

    @Id
    private Long id;

    private BigDecimal amount;

    private Long accountTypeId;

    private AccountType accountType;

    private Long customerId;

    private Customer customer;

    @DateTimeFormat(pattern = TRANSACTION_DATE_FORMAT)
    private Date transactionDate;

    public static Transaction mapToTransaction(String[] fields) {
        if (fields.length != TRANSACTION_FIELDS_NUMBER) {
            throw new InvalidNumberOfFieldsException("Invalid number of transaction object fields.");
        }

        return createTransaction(fields);
    }

    private static Transaction createTransaction(String[] fields) {
        Transaction transaction = new Transaction();
        transaction.setId(Long.parseLong(fields[0]));
        transaction.setAmount(new BigDecimal(fields[1].replaceAll(COMMA_SEPARATOR, DOT_SEPARATOR)));
        transaction.setAccountTypeId(Long.parseLong(fields[2]));
        transaction.setCustomerId(Long.parseLong(fields[3]));
        transaction.setTransactionDate(DateUtils.createDateWithFormat(fields[4], TRANSACTION_DATE_FORMAT));

        return transaction;
    }

    public static Transaction mapFromBankDocument(BankDocument bankDocument) {
        return (Transaction) bankDocument;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAccountTypeName() {
        return this.accountType.getName();
    }

    public String getCustomerFirstName() {
        return this.customer.getFirstName();
    }

    public String getCustomerLastName() {
        return this.customer.getLastName();
    }

}

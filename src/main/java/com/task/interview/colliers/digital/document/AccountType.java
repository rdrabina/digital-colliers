package com.task.interview.colliers.digital.document;

import com.task.interview.colliers.digital.exception.InvalidNumberOfFieldsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.task.interview.colliers.digital.document.constant.Constants.ACCOUNT_TYPE_FIELDS_NUMBER;

@Document
@Getter
@Setter
@NoArgsConstructor
public class AccountType {

    @Id
    private Long accountType;

    private String name;

    public static AccountType mapToAccountType(String[] fields) {
        if (fields.length != ACCOUNT_TYPE_FIELDS_NUMBER) {
            throw new InvalidNumberOfFieldsException("Invalid number of account type object fields.");
        }

        return createAccountType(fields);
    }

    private static AccountType createAccountType(String[] fields) {
        AccountType accountType = new AccountType();
        accountType.setAccountType(Long.parseLong(fields[0]));
        accountType.setName(fields[1]);

        return accountType;
    }

}


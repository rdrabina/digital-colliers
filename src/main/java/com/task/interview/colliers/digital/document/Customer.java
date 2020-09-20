package com.task.interview.colliers.digital.document;

import com.task.interview.colliers.digital.exception.InvalidNumberOfFieldsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

import static com.task.interview.colliers.digital.document.constant.Constants.CUSTOMER_FIELDS_NUMBER;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BankDocument {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal lastLoginBalance;

    public static Customer mapToCustomer(String[] fields) {
        if (fields.length != CUSTOMER_FIELDS_NUMBER) {
            throw new InvalidNumberOfFieldsException("Invalid number of customer object fields.");
        }

        return createCustomer(fields);
    }

    private static Customer createCustomer(String[] fields) {
        Customer customer = new Customer();
        customer.setId(Long.parseLong(fields[0]));
        customer.setFirstName(fields[1]);
        customer.setLastName(fields[2]);
        customer.setLastLoginBalance(new BigDecimal(fields[3].replaceAll(",", ".")));

        return customer;
    }

    public static Customer mapFromBankDocument(BankDocument bankDocument) {
        return (Customer) bankDocument;
    }

}

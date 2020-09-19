package com.task.interview.colliers.digital.dto;

import com.task.interview.colliers.digital.document.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal lastLoginBalance;

    public static CustomerDto mapFromCustomer(Customer customer) {
//        todo nullpointer
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setLastLoginBalance(customer.getLastLoginBalance());

        return customerDto;
    }

}

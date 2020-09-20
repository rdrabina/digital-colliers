package com.task.interview.colliers.digital.controller;

import com.task.interview.colliers.digital.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .build();
    }

    @Test
    public void getTransactionsWithCustomerId() throws Exception {
        mockTransactionService();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/transactions/",
                        1L)
                        .param("customerId", "1,2,3")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(transactionService, times(1)).getTransactions("ALL", "1,2,3");
    }

    @Test
    public void getTransactionsWithCustomerIdAndAccountType() throws Exception {
        mockTransactionService();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/transactions/",
                        1L)
                        .param("customerId", "1,2,3")
                        .param("accountType", "3,2,1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(transactionService, times(1)).getTransactions("3,2,1", "1,2,3");
    }

    @Test
    public void getTransactions() throws Exception {
        mockTransactionService();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/transactions/",
                        1L)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(transactionService, times(1)).getTransactions("ALL", "ALL");
    }

    private void mockTransactionService() {
        when(transactionService.getTransactions(anyString(), anyString()))
                .thenReturn(Collections.emptyList());
    }

}
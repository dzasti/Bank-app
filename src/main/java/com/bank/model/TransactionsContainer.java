package com.bank.model;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
@Getter
public class TransactionsContainer {
    private final List<Transaction> transactions;
    private final List<FeeWages> feeWages;

    TransactionsContainer() throws IOException {
        transactions = CSVParser.getTransactions();
        feeWages = CSVParser.getFeeWages();
        transactions.forEach(item -> System.out.println(item.getCustomerFirstName()));
        feeWages.forEach(item -> System.out.println(item.getFeePercentageOfTransactionValue()));
    }
}

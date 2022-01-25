package com.bank.model;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVParserTest {
    private static final String FEE_WAGES_FILE_PATH = "META-INF/fee_wages.csv";
    private static final String TRANSACTIONS_FILE_PATH = "META-INF/transactions.csv";

    @Test
    void parserShouldReturn52Transactions() throws IOException {
        List<Transaction> transactions = CSVParser.getTransactions(TRANSACTIONS_FILE_PATH);

        assertEquals(52, transactions.size());
    }

    @Test
    void parserShouldReturn3FeeWages() throws IOException {
        List<FeeWages> feeWages = CSVParser.getFeeWages(FEE_WAGES_FILE_PATH);

        assertEquals(4, feeWages.size());
    }
}

package com.bank.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerStatisticsCalculatorTest{
    private static Customer customer;

    @BeforeAll
    static void initialize() throws ParseException {
        CustomerStatisticsCalculator calculator = new CustomerStatisticsBasicCalculator();
        int id = 12;

        List<Transaction> transactions = new ArrayList<>(List.of(
                new Transaction(1, new BigDecimal(1200), "Adam", id, "Danowski", "11.12.2020 14:57:22"),
                new Transaction(1, new BigDecimal(200), "Adam", id, "Danowski", "11.12.2021 14:57:22"),
                new Transaction(1, new BigDecimal(200), "Adam", id, "Danowski", "11.12.2022 14:57:22")
        ));

        List<FeeWages> wages = new ArrayList<>(List.of(
                new FeeWages(new BigDecimal("1.0"), new BigInteger(String.valueOf(1000))),
                new FeeWages(new BigDecimal("2.0"), new BigInteger(String.valueOf(2000))),
                new FeeWages(new BigDecimal("3.0"), new BigInteger(String.valueOf(3000)))
        ));

        BigDecimal totalFeeValue = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            BigDecimal feeValueForSingleTransaction = calculator.calculateTransactionFeeValue(wages,transaction);
            totalFeeValue = totalFeeValue.add(feeValueForSingleTransaction);
        }

        customer = new Customer(
                calculator.retrieveFirstName(transactions),
                calculator.retrieveLastName(transactions),
                id,
                calculator.calculateNumberOfTransactions(transactions),
                calculator.calculateTotalValueOfTransactions(transactions),
                totalFeeValue,
                calculator.calculateLastTransactionDate(transactions)
        );
    }

    @Test
    void customerShouldHave3Transactions() {
        assertEquals(3,customer.getNumberOfTransactions());
    }

    @Test
    void customerShouldHave1600TotalValueOfTransactions() {
        assertEquals(new BigDecimal(1600), customer.getTotalValueOfTransactions());
    }

    @Test
    void customerShouldHave28TransactionsFeeValue() {
        assertEquals("28.00" ,customer.getTransactionsFeeValue().toString());
    }

    @Test
    void customerShouldHaveLastTransactionDateAsFirstFebruary2021() {
        assertEquals("11.12.2022 2:57:22 PM" ,customer.getLastTransactionDate().toString());
    }
}

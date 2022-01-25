package com.bank.model;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomerStatisticsCalculator {
    String retrieveFirstName(List<Transaction> transactions);

    String retrieveLastName(List<Transaction> transactions);

    Integer calculateNumberOfTransactions(final List<Transaction> transactions);

    Date calculateLastTransactionDate(final List<Transaction> transactions);

    BigDecimal calculateTransactionFeeValue(final List<FeeWages> wages, Transaction transaction);

    BigDecimal calculateTotalValueOfTransactions(final List<Transaction> transactions);
}
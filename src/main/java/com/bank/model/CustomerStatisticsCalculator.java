package com.bank.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CustomerStatisticsCalculator {
    String retrieveFirstName(List<Transaction> transactions);

    String retrieveLastName(List<Transaction> transactions);

    Integer calculateNumberOfTransactions(final List<Transaction> transactions);

    Date calculateLastTransactionDate(final List<Transaction> transactions) throws ParseException;

    BigDecimal calculateTransactionFeeValue(final List<FeeWages> wages, Transaction transaction);

    BigDecimal calculateTotalValueOfTransactions(final List<Transaction> transactions);
}

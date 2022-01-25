package com.bank.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class CustomerStatisticsBasicCalculator implements CustomerStatisticsCalculator {

    public String retrieveFirstName(List<Transaction> transactions) {
        return transactions.get(0).getCustomerFirstName();
    }

    public String retrieveLastName(List<Transaction> transactions) {
        return transactions.get(0).getCustomerLastName();
    }

    public Integer calculateNumberOfTransactions(final List<Transaction> transactions) {
        return transactions.size();
    }

    public Date calculateLastTransactionDate(final List<Transaction> transactions) {
        return Collections.min(transactions, (u1, u2) -> u2.getTransactionDate().compareTo(u1.getTransactionDate())).getTransactionDate();
    }

    public BigDecimal calculateTransactionFeeValue(List<FeeWages> wages, Transaction transaction) {
        BigInteger _transactionAmount = transaction.getTransactionAmount().toBigInteger();
        BigDecimal desirableFeeValue = wages.get(0).getFeePercentageOfTransactionValue();
        for (FeeWages value : wages) {
            if (_transactionAmount.compareTo(value.getTransactionValueLessThan()) < 0 ||
            _transactionAmount.compareTo(value.getTransactionValueLessThan()) == 0) {
                desirableFeeValue = value.getFeePercentageOfTransactionValue();
                break;
            }
        }
        System.out.println(desirableFeeValue);
        return desirableFeeValue;
    }

    public BigDecimal calculateTotalValueOfTransactions(final List<Transaction> transactions) {
        BigDecimal totalValue = new BigDecimal(0);
        for (Transaction item : transactions) {
            totalValue = totalValue.add(item.getTransactionAmount());
        }
        return totalValue;
    }
}

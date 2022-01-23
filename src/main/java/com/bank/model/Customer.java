package com.bank.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class Customer {
    @SerializedName(value = "First name")
    private final String name;
    @SerializedName(value = "Last name")
    private final String surname;
    @SerializedName(value = "Customer ID")
    private final Integer id;
    @SerializedName(value = "Number of transactions")
    private final Integer numberOfTransactions;
    @SerializedName(value = "Total value of transactions")
    private final BigDecimal totalValueOfTransactions;
    @SerializedName(value = "Transactions fee value")
    private final BigDecimal transactionsFeeValue;
    @SerializedName(value = "Last transaction date")
    private final Date lastTransactionDate;

    public Customer(Integer id, List<Transaction> transactions, List<FeeWages> wages) {
        this.id = id;
        this.name = retrieveFirstName(transactions);
        this.surname = retrieveLastName(transactions);
        this.numberOfTransactions = calculateNumberOfTransactions(transactions);
        this.totalValueOfTransactions = calculateTotalValueOfTransactions(transactions);
        this.lastTransactionDate = calculateLastTransactionDate(transactions);
        this.transactionsFeeValue = calculateFeeValue(wages);

    }

    private String retrieveFirstName(List<Transaction> transactions) {
        return transactions.get(0).getCustomerFirstName();
    }

    private String retrieveLastName(List<Transaction> transactions) {
        return transactions.get(0).getCustomerLastName();
    }

    private Integer calculateNumberOfTransactions(final List<Transaction> transactions) {
        return transactions.size();
    }

    private BigDecimal calculateTotalValueOfTransactions(final List<Transaction> transactions) {
        BigDecimal totalValue = new BigDecimal(0);
        for (Transaction item : transactions) {
            totalValue = totalValue.add(item.getTransactionAmount());
        }
        return totalValue;
    }

    private Date calculateLastTransactionDate(final List<Transaction> transactions) {
        return Collections.min(transactions, (u1, u2) -> u2.getTransactionDate().compareTo(u1.getTransactionDate())).getTransactionDate();
    }

    private BigDecimal calculateFeeValue(final List<FeeWages> wages) {
        BigInteger _totalValueOfTransactions = this.totalValueOfTransactions.toBigInteger();
        BigDecimal desirableFeeValue = wages.get(0).getFeePercentageOfTransactionValue();
        for (FeeWages value : wages) {
            if (_totalValueOfTransactions.compareTo(value.getTransactionValueLessThan()) < 0 ||
                    _totalValueOfTransactions.compareTo(value.getTransactionValueLessThan()) == 0) {
                desirableFeeValue = value.getFeePercentageOfTransactionValue();
                break;
            }
        }
        return desirableFeeValue;
    }
}

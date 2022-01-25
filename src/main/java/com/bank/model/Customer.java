package com.bank.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Date;

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
    private BigDecimal totalValueOfTransactions;

    @SerializedName(value = "Transactions fee value")
    private BigDecimal transactionsFeeValue;

    @SerializedName(value = "Last transaction date")
    private final Date lastTransactionDate;

    public Customer(String aName, String aSurname, Integer aId, Integer aNumberOfTransactions,
                    BigDecimal aTotalValueOfTransactions, BigDecimal aTransactionsFeeValue,
                    Date aLastTransactionDate) {
        this.id = aId;
        this.name = aName;
        this.surname = aSurname;
        this.numberOfTransactions = aNumberOfTransactions;
        this.lastTransactionDate = aLastTransactionDate;
        this.totalValueOfTransactions = aTotalValueOfTransactions;
        this.transactionsFeeValue = aTransactionsFeeValue;
    }
}

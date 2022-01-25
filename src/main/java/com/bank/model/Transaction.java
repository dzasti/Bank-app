package com.bank.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.Getter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@JsonPropertyOrder({ "transactionId", "transactionAmount", "customerFirstName", "customerId","customerLastName","transactionDate" })
public class Transaction{

    private final Integer transactionId;

    @JsonDeserialize(using = BigDecimalSerializer.class)
    private final BigDecimal transactionAmount;

    private final String customerFirstName;

    private final Integer customerId;

    private final String customerLastName;

    private final String transactionDateStr;

    @Getter(AccessLevel.NONE)
    private Date transactionDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Transaction(@JsonProperty("transaction_id") Integer aTransactionId,
                       @JsonProperty("transaction_amount")BigDecimal aTransactionAmount,
                       @JsonProperty("customer_first_name") String aCustomerFirstName,
                       @JsonProperty("customer_id") Integer aCustomerId,
                       @JsonProperty("customer_last_name") String aCustomerLastName,
                       @JsonProperty("transaction_date") String aTransactionDate) {

        this.transactionId = aTransactionId;
        this.transactionAmount = aTransactionAmount;
        this.customerFirstName = aCustomerFirstName;
        this.customerId = aCustomerId;
        this.customerLastName = aCustomerLastName;
        this.transactionDateStr = aTransactionDate;
    }

    public void formatDate() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        transactionDate = dateFormat.parse(String.valueOf(this.transactionDateStr));
    }

    @JsonIgnore
    public Date getTransactionDate() {
        return transactionDate;
    }
}

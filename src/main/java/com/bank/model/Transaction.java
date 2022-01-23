package com.bank.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@JsonPropertyOrder({ "transactionId", "transactionAmount", "customerFirstName", "customerId","customerLastName","transactionDate" })
public class Transaction {

    private final Integer transactionId;
    @JsonDeserialize(using = BigDecimalSerializer.class)
    private final BigDecimal transactionAmount;
    private final String customerFirstName;
    private final Integer customerId;
    private final String customerLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    private final Date transactionDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Transaction(@JsonProperty("transaction_id") Integer aTransactionId,
                       @JsonProperty("transaction_amount")BigDecimal aTransactionAmount,
                       @JsonProperty("customer_first_name") String aCustomerFirstName,
                       @JsonProperty("customer_id") Integer aCustomerId,
                       @JsonProperty("customer_last_name") String aCustomerLastName,
                       @JsonProperty("transaction_date") Date aTransactionDate) {

        this.transactionId = aTransactionId;
        this.transactionAmount = aTransactionAmount;
        this.customerFirstName = aCustomerFirstName;
        this.customerId = aCustomerId;
        this.customerLastName = aCustomerLastName;
        this.transactionDate = aTransactionDate;
    }

}

package com.bank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@JsonPropertyOrder({ "transactionValueLessThan", "feePercentageOfTransactionValue"})
public class FeeWages {

    private final BigInteger transactionValueLessThan;

    @JsonDeserialize(using = BigDecimalSerializer.class)
    private final BigDecimal feePercentageOfTransactionValue;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FeeWages(@JsonProperty("fee_percentage_of_transaction_value") BigDecimal aFeePercentageOfTransactionValue,
                    @JsonProperty("transaction_value_less_than") BigInteger aTransactionValueLessThan
                    ) {

        this.transactionValueLessThan = aTransactionValueLessThan;
        this.feePercentageOfTransactionValue = aFeePercentageOfTransactionValue;
    }

}

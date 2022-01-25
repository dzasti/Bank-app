package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Document
public class TransactionWithFee {
    private final Integer clientId;
    private final BigDecimal amountOfCommission;
    private final Date todayDate;
}

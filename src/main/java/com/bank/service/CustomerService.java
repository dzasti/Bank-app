package com.bank.service;

import com.bank.model.*;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService{
    private final static String TRANSACTIONS_FILE_PATH = "META-INF/transactions.csv";
    private final static String FEE_WAGES_FILE_PATH = "META-INF/fee_wages.csv";
    private final List<Customer> customers;

    @Autowired
    CustomerService(TransactionRepository transactionRepository,
                    CustomerStatisticsCalculator aCalculator) throws IOException, ParseException {

        this.customers = new ArrayList<>();

        Map<Integer,List<Transaction>> customerIdAndTransactionsMap = CSVParser.getTransactions(TRANSACTIONS_FILE_PATH).stream().collect(Collectors.groupingBy(Transaction::getCustomerId));
        List<FeeWages> feeWages = CSVParser.getFeeWages(FEE_WAGES_FILE_PATH);
        feeWages.sort(Comparator.comparing(FeeWages::getTransactionValueLessThan));

        for (var entry : customerIdAndTransactionsMap.entrySet()) {
            BigDecimal totalFeeValue = new BigDecimal(0);
            for (Transaction transaction : entry.getValue()) {
                BigDecimal feeValueForSingleTransaction = aCalculator.calculateTransactionFeeValue(feeWages,transaction);
                transactionRepository.saveAll(List.of( new TransactionWithFee(entry.getKey(),feeValueForSingleTransaction,new Date()) ));
                totalFeeValue = totalFeeValue.add(feeValueForSingleTransaction);
            }
            Customer customer = new Customer(
                    aCalculator.retrieveFirstName(entry.getValue()),
                    aCalculator.retrieveLastName(entry.getValue()),
                    entry.getKey(),
                    aCalculator.calculateNumberOfTransactions(entry.getValue()),
                    aCalculator.calculateTotalValueOfTransactions(entry.getValue()),
                    totalFeeValue,
                    aCalculator.calculateLastTransactionDate(entry.getValue())
            );
            customers.add(customer);

        }
        customers.forEach(System.out::println);
    }

    public List<Customer> getCustomersById(List<Integer> idList) {
        return customers.stream().filter(item -> idList.contains(item.getId())).collect(Collectors.toList());
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

}

package com.bank.repository;

import com.bank.model.CSVParser;
import com.bank.model.Customer;
import com.bank.model.FeeWages;
import com.bank.model.Transaction;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerRepository {
    private final List<Customer> customers;
    private final List<FeeWages> feeWages;

    CustomerRepository() throws IOException {
        customers = new ArrayList<>();
        Map<Integer,List<Transaction>> customerIdAndTransactionsMap = CSVParser.getTransactions().stream().collect(Collectors.groupingBy(Transaction::getCustomerId));
        feeWages = CSVParser.getFeeWages();
        feeWages.sort(Comparator.comparing(FeeWages::getTransactionValueLessThan));

        for (var entry : customerIdAndTransactionsMap.entrySet()) {
            customers.add( new Customer(entry.getKey(),entry.getValue(),feeWages));
        }
        customers.forEach(item -> System.out.println(item));
    }

    public List<Customer> getCustomerById(List<Integer> idList) {
        return customers.stream().filter(item -> idList.contains(item.getId())).collect(Collectors.toList());
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}

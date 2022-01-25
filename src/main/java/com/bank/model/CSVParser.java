package com.bank.model;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class CSVParser {
    public static List<Transaction> getTransactions(String pathToTransactionFile) throws IOException {
        Reader reader = new InputStreamReader(Objects.requireNonNull(CSVParser.class.getClassLoader().getResourceAsStream(pathToTransactionFile)));
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(Transaction.class).withHeader();
        MappingIterator<Transaction> transactions = csvMapper.readerWithTypedSchemaFor(Transaction.class).with(csvSchema).readValues(reader);

        return transactions.readAll();
    }

    public static List<FeeWages> getFeeWages(String pathToFeeWagesFile) throws IOException {
        Reader reader = new InputStreamReader(Objects.requireNonNull(CSVParser.class.getClassLoader().getResourceAsStream(pathToFeeWagesFile)));
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(FeeWages.class).withHeader();
        MappingIterator<FeeWages> feeWages = csvMapper.readerWithTypedSchemaFor(FeeWages.class).with(csvSchema).readValues(reader);

        return feeWages.readAll();
    }
}

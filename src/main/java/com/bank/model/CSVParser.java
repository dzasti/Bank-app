package com.bank.model;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.util.List;

public class CSVParser {

    public static List<Transaction> getTransactions() throws IOException {
        //xd popraw
        Reader reader = new BufferedReader(new FileReader("src/main/resources/META-INF/transactions.csv"));
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(Transaction.class).withHeader();
        MappingIterator<Transaction> personIter = csvMapper.readerWithTypedSchemaFor(Transaction.class).with(csvSchema).readValues(reader);
        return personIter.readAll();
    }

    public static List<FeeWages> getFeeWages() throws IOException {
        // xd popraw
        Reader reader = new BufferedReader(new FileReader("src/main/resources/META-INF/fee_wages.csv"));
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(FeeWages.class).withHeader();
        MappingIterator<FeeWages> personIter = csvMapper.readerWithTypedSchemaFor(FeeWages.class).with(csvSchema).readValues(reader);
        return personIter.readAll();
    }
}

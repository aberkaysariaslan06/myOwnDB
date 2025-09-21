package com.javaDB.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.javaDB.csv.beans.Column;
import com.javaDB.csv.beans.FieldValue;
import com.javaDB.csv.beans.Filter;
import com.javaDB.csv.beans.Order;
import com.javaDB.csv.beans.Row;
import com.javaDB.csv.beans.SearchResult;
import com.javaDB.exceptions.DatabaseException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CsvDatabaseEngineImp implements DatabaseEngine {

    private String basePath; // verileri depolamak adina kullanacagiz.
    private static final String COMMA = ",";

    @Override
    public void createTable(String tableName, List<Column> columns) {
        verifyTableDoesNotExist(tableName);
        String tableFile = basePath + File.pathSeparator + tableName + ".csv";
        performActionInTable(tableName, true, bw -> {
            String headerLine = columns.stream()
                    .map(column -> column.getName().trim()) // trim demek bir stringin onundeki ve arkasindaki
                    // bosluklari temizler
                    .collect(Collectors.joining(COMMA)); // virgullerle ayrilacagiz.
            writeContent(bw, headerLine, tableName);

        });


    }

    @Override
    public int deleteFromTable(String tableName, List<Filter> filters) {

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void dropTable(String tableName) {
        // TODO Auto-generated method stub

    }

    @Override
    public int insertIntoTable(String tableName, List<Row> rows) {
        // TODO Auto-generated method stub
        verifyTableExists(tableName);
        return performActionInTable(tableName, true, bw -> {
            rows.stream()
                    .map(Row::getFields)
                    .map(this::escapeCharacters)
                    .forEach(line -> {
                        writeContent(bw, line, tableName);

                    });
            return rows.size();

        });


    }
    private void writeContent(BufferedWriter bw, String text, String tableName){
        try {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            throw new DatabaseException("Error when writing to file : " + e.getMessage() + "in table :" + tableName);
        }
    }
    private String escapeCharacters(List<String> values) {
        return values.stream()
                .map(this::escapeDoubleQuotes)   // her elemanı ayrı metoda veriyoruz
                .collect(Collectors.joining(", "));
    }

    private String escapeDoubleQuotes(String s) {
        String returnValue = s;
        if (s.contains("\"")) {
            returnValue = s.replace("\"", "\\\"");
        }
        return "\"" + returnValue + "\"";
    }


    @Override
    public SearchResult selectFromTable(String tableName, List<String> fields, List<Filter> filters, Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateTable(String tableName, List<FieldValue> updatedValue, List<Filter> filters) {
        // TODO Auto-generated method stub
        return 0;
    }
    private String fileNameForTable(String tableName) {
        return  basePath + File.separator + tableName + ".csv";

    }
    private void verifyTableDoesNotExist(String tableName) {
        if (tableExists(tableName))
            throw new DatabaseException("Table : " + tableName + "does not exist ! ");
    }
    private void verifyTableExists(String tableName) {
        if (!tableExists(tableName))
            throw new DatabaseException("Table : " + tableName + "does not exist ! ");

    }
    private boolean tableExists(String tableName){
        String tableFile = fileNameForTable(tableName);
        return new File(tableFile).exists();
    }
    private <T> T performActionInTable(String tableName, boolean append, Function<BufferedWriter, T> function){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileNameForTable(tableName), append)))  {
            return function.apply(bw);

        } catch (IOException ex) {
            throw new DatabaseException("Error when creating the file  :  " + tableName);
        }
    }
    private void performActionInTable(String tableName, boolean append, Consumer<BufferedWriter> consumer){
        performActionInTable(tableName, append, bufferedWriter -> {
            consumer.accept(bufferedWriter);
            return 0;
                });


    }

}

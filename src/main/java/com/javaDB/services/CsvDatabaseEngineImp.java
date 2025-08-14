package com.javaDB.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
        String tableFile = basePath + File.pathSeparatorChar + tableName + ".csv";
        // pathSepeartorChar kullanarak OS'a uygun dosya ayristirma karakteri
        // kullanilabilir.
        try {
            // yaptigimiz islemleri dosyaya yazmak icin FileWriter kullaniyoruz
            FileWriter fileWriter = new FileWriter(tableFile, true); // append true ile dosyaya surekli ekleme
                                                                     // yapilabilir.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String headerLine = columns.stream()
                    .map(column -> column.getName().trim()) // trim demek bir stringin onundeki ve arkasindaki
                                                            // bosluklari temizler
                    .collect(Collectors.joining(COMMA)); // virgullerle ayrilacagiz.
            bufferedWriter.write(headerLine);
            bufferedWriter.write("..dosyanin icine istedigimizi yazabiliriz..");
            bufferedWriter.newLine();

        } catch (IOException ex) {
            throw new DatabaseException("Error when creating the file  :  " + tableFile);
        }

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
        return 0;
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

}

package com.javaDB.services;

import java.util.List;

import com.javaDB.csv.beans.Column;
import com.javaDB.csv.beans.FieldValue;
import com.javaDB.csv.beans.Filter;
import com.javaDB.csv.beans.Order;
import com.javaDB.csv.beans.Row;
import com.javaDB.csv.beans.SearchResult;

public interface DatabaseEngine {

    void createTable(String tableName, List<Column> columns); // tablo schemasi olusturulur. column tipinde ad, veri
                                                              // tipi, null vb. tasiyabilir.

    int insertIntoTable(String tableName, List<Row> rows); // kac satir eklendigini dondurur.

    SearchResult selectFromTable(String tableName, List<String> fields, List<Filter> filters, Order order); // Search
                                                                                                            // Result
                                                                                                            // ile
                                                                                                            // satirlar
                                                                                                            // + toplam
                                                                                                            // sayiyi
                                                                                                            // donebilirz.

    int deleteFromTable(String tableName, List<Filter> filters);

    void dropTable(String tableName);

    int updateTable(String tableName, List<FieldValue> updatedValue, List<Filter> filters);

}

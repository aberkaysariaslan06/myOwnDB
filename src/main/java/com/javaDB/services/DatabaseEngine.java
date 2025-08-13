package main.java.com.javaDB.services;

public interface DatabaseEngine {

    void createTable(String tableName, List<Column> columns);

    int insertIntoTable(String tableName, List<Row> rows);

    SearchResult selectFromTable(String tableName, List<String> fields, List<Filter> filters);

    int deleteFromTable(String tableName, List<Filter> filters);

    void dropTable(String tableName);

    int updateTable(String tableName, List<FieldValue> updatedValue, List<Filter> filters);

}

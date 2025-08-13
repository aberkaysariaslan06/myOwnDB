package main.java.com.javaDB.services;

public class CsvDatabaseEngineImp implements DatabaseEngine {

    @Override
    public void createTable(String tableName, List<Column> columns) {
        // TODO Auto-generated method stub

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
    public void test(ListAllOfThem list) {
        // TODO Auto-generated method stub

    }

    @Override
    public int updateTable(String tableName, List<FieldValue> updatedValue, List<Filter> filters) {
        // TODO Auto-generated method stub
        return 0;
    }

}

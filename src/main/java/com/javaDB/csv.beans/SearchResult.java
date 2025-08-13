package main.java.com.javaDB.csv.beans;

@AllArgsConsturctor
@Getter
public class SearchResult {

    private final String tablenName;
    private final List<Column> columns;
    private final List<Row> foundRows;
}

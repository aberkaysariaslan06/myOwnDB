package com.javaDB.csv.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchResult {

    private String tablenName;
    private List<Column> columns;
    private List<Row> foundRows;
}

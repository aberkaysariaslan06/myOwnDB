package com.javaDB.csv.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Row {
    private final int id;
    private final List<String> fields;

    public static Row newRow (List<String> fields) {
        return new Row (0, fields);
    }
}

package com.javaDB.csv.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Column {
    public static final String STRING_COLUMN = "String";
    private String name;
    private String dataType;

    public static Column stringColumn(String name) {
        return new Column(name, STRING_COLUMN);
    }

    // bu proje icin sadece String veri tipi kullanilacak.
}

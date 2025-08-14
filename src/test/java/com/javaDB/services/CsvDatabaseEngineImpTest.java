package com.javaDB.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.javaDB.csv.beans.Column;
import static com.javaDB.csv.beans.Column.stringColumn;

public class CsvDatabaseEngineImpTest {

    private DatabaseEngine databaseEngine;
    private Path tempDir;

    // @TempDir //her defasinda gecici bir lokasyon gerekli.d
    // Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("java-db-test");
        System.out.println("Temp Dir : " + tempDir.toString());
        databaseEngine = new CsvDatabaseEngineImp(tempDir.toString());
        System.out.println("Before Each "); // ilk cagrilan

    }

    @AfterEach
    void tearDown() throws IOException {
        // Files.walk(tempDir)
        // .sorted(Comparator.reverseOrder())
        // .forEach(p -> {
        // try {
        // Files.deleteIfExists(p);
        // } catch (IOException ignored) {
        // }
        // });

        System.out.println("After Each "); // en son cagrilab

    }

    @Test
    void testCreateTable() {
        databaseEngine.createTable("Baris",
                List.of(

                        stringColumn("id"),
                        stringColumn("firstName"),
                        stringColumn("lastName")));
        System.out.println("Test Create Table Test "); // ikinci cagrilan

    }
}

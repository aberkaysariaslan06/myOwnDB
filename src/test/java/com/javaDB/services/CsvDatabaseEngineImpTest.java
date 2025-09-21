package com.javaDB.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import com.javaDB.csv.beans.Row;
import org.assertj.core.api.AbstractFileAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;


import com.javaDB.csv.beans.Column;
import static com.javaDB.csv.beans.Column.stringColumn;

public class CsvDatabaseEngineImpTest {

    private DatabaseEngine databaseEngine;
    private Path tempDir;
    private static final String TEST_TABLE = "MY_TABLE";

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

        assertThatFile()
                .exists()
                .hasContent("id,firstName,lastName");

    }
    @Test
    void testInsertTable() {
        createTableTestTable();

        int rows = databaseEngine.insertIntoTable(TEST_TABLE,
                List.of(
                        Row.newRow(List.of("1", "Beril", "Ayhan")),
                        Row.newRow(List.of("7", "Ahme\"t", "Ber,k,,ay"))
                ));

        assertThat(rows).isEqualTo(2);
        assertThatFile()
                .exists()
                .hasContent("""
                    id,firstName,lastName
                    "1", "Beril", "Ayhan"
                    "7", "Ahme\\"t", "Ber,k,,ay"
                    """);
    }



    private AbstractFileAssert<?> assertThatFile() {
        return assertThat(new File(tempDir + File.separator + TEST_TABLE + ".csv"));
    }

    private void createTableTestTable(){
        databaseEngine.createTable(TEST_TABLE,
                List.of(
                        stringColumn("id"),
                        stringColumn("firstName"),
                        stringColumn("lastName")));
    }
}

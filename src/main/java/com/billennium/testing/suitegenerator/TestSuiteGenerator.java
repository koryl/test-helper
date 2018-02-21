package com.billennium.testing.suitegenerator;

import com.billennium.testing.logger.Log;
import com.billennium.testing.suitegenerator.xlsreader.XlsConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.billennium.testing.Constants.QUERY_PATH;
import static com.billennium.testing.Constants.TEST_DATA;
import static java.util.Objects.isNull;

public class TestSuiteGenerator {

    public synchronized static void generate() {

        XlsConverter suite = new XlsConverter(TEST_DATA);

        String query = null;
        try {
            query = Files.readAllLines(Paths.get(QUERY_PATH)).get(0);
        } catch (IOException e) {
            Log.error("Cannot read query.txt file: " + e.getMessage());
        } finally {
            if (isNull(query)) {
                query = "SELECT * FROM TESTCASES";
                suite.getTests(query);
            } else {
                suite.getTests(query);
            }
        }
    }
}

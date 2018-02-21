package com.billennium.testing.suitegenerator;

import com.billennium.testing.logger.Log;
import com.billennium.testing.suitegenerator.xlsreader.XlsConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.billennium.testing.Constants.QUERY_PATH;
import static com.billennium.testing.Constants.TEST_DATA;
import static java.util.Objects.isNull;

/**
 * <code><b>TestSuiteGenerator</b></code> is created for generating custom TestNG suites in runtime. To actually use
 * <code><b>TestSuiteGenerator</b></code> it must be implemented TestNG <code>IAlterSuiteListener</code> for passing a generated
 * test suite. It must be also provided .xls file which contains data of your test cases in Sheet named <i>TESTCASES/i>.
 * A spreadsheet is treated as a database from which you could choose which test cases would be run. For this
 * purpose query.txt file should be provided.
 */
public abstract class TestSuiteGenerator {

    /**
     * Generates custom TestNG suite.
     * Note: it must be provided .xls file with name of classes and query.txt file which contains query to execute.
     */
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

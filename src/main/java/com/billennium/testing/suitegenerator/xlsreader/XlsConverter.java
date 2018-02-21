package com.billennium.testing.suitegenerator.xlsreader;

import com.billennium.testing.logger.Log;
import com.billennium.testing.suitegenerator.suite.Suite;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.List;

import static com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature.WRITE_XML_DECLARATION;
import static java.util.Arrays.asList;

public class XlsConverter {

    private final Fillo fillo;
    private final String filePath;

    private Connection connection;

    public XlsConverter(String filePath) {
        fillo = new Fillo();
        this.filePath = filePath;
    }

    public void getTests(String query) {
        try {
            connection = fillo.getConnection(this.filePath);
            Recordset recordset = connection.executeQuery(query);
            this.createSuite(recordset);
        } catch (FilloException e) {
            Log.error("Exception: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    private void createSuite(Recordset recordset) {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(WRITE_XML_DECLARATION, true);
        Suite suite = new Suite("Dependomat");
        try {

            while (recordset.next()) {
                String testName = recordset.getField("TestCaseDescription");
                String className = recordset.getField("ClassName");
                if(recordset.getField("Data").isEmpty()) {
                    suite.addTest(testName, className);
                } else {
                    List<String> params = asList(recordset.getField("Data").split(","));
                    suite.addTest(testName, params, className);
                }
            }
            xmlMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("src/test/resources/dependomat-testng.xml"), suite);
            Log.debug("Test suite was generated properly.");
        } catch (Exception e) {
            Log.error("Cannot create test suites: " + e.getMessage());
        } finally {
            recordset.close();
        }
    }

}
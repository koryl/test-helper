package com.billennium.testing;

public class Constants {

    public static final String PROJECT_NAME = System.getProperty("test.project.name");
    public static final String RUN_NAME = System.getProperty("test.run.name");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String TEST_OUTPUT = System.getProperty("output.directory");
    public static final String TEST_DATA = System.getProperty("setup.xls.file");
    public static final String DRIVER_PATH = System.getProperty("setup.driver.file");
    public static final String QUERY_PATH = System.getProperty("setup.query.file");
    public static final String TEST_DATA_DIRECTORY = System.getProperty("setup.test.data.directory");
    public static final String TEST_LOG = System.getProperty("output.test.log");

//    Used for mobile application testing
    public static final String APP_PATH = System.getProperty("setup.test.app.file");
}

package com.billennium.testing;

/**
 * All value which must be provided by user to actually use <b>TAH</b>. Parameters can by provided as Maven parameters
 * or in a property file with <i>surefire-plugin</i> with <i>systemPropertiesFile</i> tag included.
 * Bear in mind that not all parameters are mandatory
 */
public abstract class Constants {

    //    System variables (no need for initialization)
    public static final String USER_NAME = System.getProperty("user.name");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String OS_ARCH = System.getProperty("os.arch");

    //    Test output and source data
    public static final String TEST_OUTPUT = System.getProperty("output.directory");
    public static final String TEST_SETUP = System.getProperty("setup.directory");
    public static final String TEST_DATA_DIRECTORY = System.getProperty("setup.test.data.directory");
    public static final String TEST_SUITE_FILE = System.getProperty("setup.test.suite.file");
    public static final String TEST_LOG = System.getProperty("output.test.log.file");

    //    Path to WebDriver
    public static final String DRIVER_PATH = System.getProperty("setup.driver.file");

    //    Used for reporting purposes
    public static final String PROJECT_NAME = System.getProperty("test.project.name");
    public static final String RUN_NAME = System.getProperty("test.run.name");
    public static final String ENVIRONMENT_NAME = System.getProperty("environment");

    //    Used for generating custom TestNG suite
    public static final String TEST_DATA = System.getProperty("setup.xls.file");
    public static final String QUERY_PATH = System.getProperty("setup.query.file");

    //    Used for web application testing
    public static final String START_URL = System.getProperty("start-url");
    public static final String BROWSER_NAME = System.getProperty("browserName");

    //    Used for mobile application testing only
    public static final String APP_PATH = System.getProperty("mobile.test.app.file");
    public static final String PLATFORM_NAME = System.getProperty("mobile.platform.name");
    public static final String PLATFORM_VERSION = System.getProperty("mobile.platform.version");
    public static final String DEVICE_NAME = System.getProperty("mobile.device.name");
}

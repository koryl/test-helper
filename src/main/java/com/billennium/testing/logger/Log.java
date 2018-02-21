package com.billennium.testing.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Easy way to log actions without adding <code>LoggerFactory</code> in each class. It uses SLF4J Logger.
 */
public abstract class Log {

    private final static Logger Log = LoggerFactory.getLogger(Log.class);

    /**
     * Adds name of started test in logs. Should be used alongside with TestNG <code>onStart(ITestContext context)</code> method.
     * @param sTestCaseName name of the test case
     */
    public synchronized static void startTestCase(String sTestCaseName) {
        Log.info("*********************************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$$$ --- " + sTestCaseName + " --- $$$$$$$$$$$$$$$$$$$$$$$");
        Log.info("*********************************************************************************************************");
    }

    /**
     * Adds information when a test execution ends. Should be used alongside with TestNG <code>onFinish(ITestContext context)</code> method.
     */
    public synchronized static void endTestCase() {
        Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ------------E---N---D------------ XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    /**
     * Logs information messages.
     *
     * @param message message content
     */
    public synchronized static void info(String message) {
        Log.info(message);
    }

    /**
     * Logs warning messages.
     *
     * @param message message content
     */
    public synchronized static void warn(String message) {
        Log.warn(message);
    }

    /**
     * Logs error messages.
     *
     * @param message message content
     */
    public synchronized static void error(String message) {
        Log.error(message);
    }

    /**
     * Logs debug messages.
     *
     * @param message message content
     */
    public synchronized static void debug(String message) {
        Log.debug(message);
    }
}


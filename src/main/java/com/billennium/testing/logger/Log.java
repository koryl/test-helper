package com.billennium.testing.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static Logger Log = LoggerFactory.getLogger(Log.class);

    public synchronized static void startTestCase(String sTestCaseName) {

        Log.info("*********************************************************************************************************");

        Log.info("$$$$$$$$$$$$$$$$$$$$$$$ --- " + sTestCaseName + " --- $$$$$$$$$$$$$$$$$$$$$$$");

        Log.info("*********************************************************************************************************");
    }

    public synchronized static void endTestCase() {

        Log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ------------E---N---D------------ XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

    public synchronized static void info(String message) {

        Log.info(message);
    }

    public synchronized static void warn(String message) {

        Log.warn(message);
    }

    public synchronized static void error(String message) {

        Log.error(message);
    }

    public synchronized static void debug(String message) {

        Log.debug(message);
    }
}


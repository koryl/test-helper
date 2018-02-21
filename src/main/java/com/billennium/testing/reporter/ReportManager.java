package com.billennium.testing.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.billennium.testing.logger.Log;

import static com.billennium.testing.Constants.OS_NAME;
import static com.billennium.testing.Constants.TEST_OUTPUT;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest;
    private static ThreadLocal<ExtentTest> test;

    public synchronized static ExtentReports getReporter() {
        if (extent == null)
            createInstance();

        return extent;
    }

    private synchronized static void createInstance() {

        parentTest = new ThreadLocal<>();
        test = new ThreadLocal<>();

        String runName = System.getProperty("run.name");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(TEST_OUTPUT + runName + "/run-report.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Dependomat Automation Tests");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setReportName("Test run " + runName);

        extent = new ExtentReports();
        extent.setSystemInfo("User name", System.getProperty("user.name"));
        extent.setSystemInfo("OS name", OS_NAME);
        extent.setSystemInfo("OS version", System.getProperty("os.version"));
        extent.setSystemInfo("OS architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("Environment", System.getProperty("environment"));
        extent.setSystemInfo("URL", System.getProperty("start-url"));
        extent.setSystemInfo("Browser name", System.getProperty("browserName"));
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(htmlReporter);
        Log.debug("ReportManager was created.");
    }

    public synchronized static ThreadLocal<ExtentTest> getParentTest() {
        return parentTest;
    }

    public synchronized static ThreadLocal<ExtentTest> getTest() {
        return test;
    }
}

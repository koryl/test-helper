package io.github.koryl.tah.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.koryl.tah.logger.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.koryl.tah.Constants.*;

/**
 * <b>ReportManager</b> uses ExtentReport framework for generating report after test execution. It is recommend to use
 * this class alongside with TestNG <i>ITestListener</i> to control a status of a current test step.
 */
public abstract class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest;
    private static ThreadLocal<ExtentTest> test;

    /**
     * Provides an instance of ReportManager. If it does not exist, it creates one.
     * @return instance of ReportManager
     */
    public synchronized static ExtentReports getReporter() {
        if (extent == null)
            createInstance();

        return extent;
    }

    /**
     * Creates instance of ReportManager. It should be invoked exactly once during Test Execution.
     */
    private synchronized static void createInstance() {

        parentTest = new ThreadLocal<>();
        test = new ThreadLocal<>();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(TEST_OUTPUT + RUN_NAME + "/run-report.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(PROJECT_NAME + " Test Report");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setReportName("Test run: " + RUN_NAME);

        extent = new ExtentReports();
        extent.setSystemInfo("User name", USER_NAME);
        extent.setSystemInfo("OS name", OS_NAME);
        extent.setSystemInfo("OS version", OS_VERSION);
        extent.setSystemInfo("OS architecture", OS_ARCH);
        addSystemInfo();
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

    public synchronized static void closeReporter() {
        try {
            Files.readAllLines(Paths.get(TEST_LOG)).forEach(s -> getReporter().setTestRunnerOutput(s + "<br/>"));
        } catch (IOException e) {
            Log.warn("Cannot add logs to Test Execution Report.");
        } finally {
            getReporter().flush();
        }
    }

    /**
     * Adds system and application info to ReportManager.
     */
    private synchronized static void addSystemInfo() {

        if (!ENVIRONMENT_NAME.isEmpty())
            extent.setSystemInfo("Environment", ENVIRONMENT_NAME);
        if (!START_URL.isEmpty())
            extent.setSystemInfo("URL", START_URL);
        if (!BROWSER_NAME.isEmpty())
            extent.setSystemInfo("Browser name", BROWSER_NAME);
        if (!PLATFORM_NAME.isEmpty())
            extent.setSystemInfo("Platform name", PLATFORM_NAME);
        if (!PLATFORM_VERSION.isEmpty())
            extent.setSystemInfo("Platform version", PLATFORM_VERSION);
        if (!DEVICE_NAME.isEmpty())
            extent.setSystemInfo("Device name", DEVICE_NAME);
    }
}

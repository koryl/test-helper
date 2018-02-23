package io.github.koryl.tah.screenshooter;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.koryl.tah.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static io.github.koryl.tah.Constants.RUN_NAME;
import static io.github.koryl.tah.Constants.TEST_OUTPUT;
import static io.github.koryl.tah.reporter.ReportManager.getTest;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Easy way for handling with taking screenshots. Includes method for generating a file name related to an executed test
 * and adding screenshots to Test Execution Report.
 * Best way is to use this class alongside with TestNG <i>ITestListener</i> to control a status of a current test step.
 */
public abstract class ScreenshotProvider {

    /**
     * Takes a screenshot and attaches it to Test Execution Report.
     * @param testName a test name of current test method
     * @param isSuccess a status of current test method
     * @param driver an instance of WebDriver
     */
    public synchronized static void takeScreenshot(String testName, boolean isSuccess, WebDriver driver) {

        try {
            String status;
            String path;

            if (isSuccess) {
                status = "Success";
            } else {
                status = "Failed";
            }
            path = generateFilePath(status, testName);
            takeScreenshot(path, driver);
            getTest().get().info(status, MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        } catch (IOException e) {
            Log.error("Cannot add screenshot to the report: " + e.getMessage());
        }
    }

    private synchronized static void takeScreenshot(String path, WebDriver driver) {

        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(path);
        try {
            FileUtils.copyFile(srcFile, destFile);
            Log.debug("Screenshot was taken.");
        } catch (IOException e) {
            Log.error("Cannot to create the screenshot in the target path: " + e.getMessage());
        }
    }

    private synchronized static String generateFilePath(String status, String testName) {

        String date = now().format(ofPattern("yyyy-MM-dd_HHmmss"));

        String fileName = testName.replaceAll(" ", "_") + "_" + status + "_" + date;

        return System.getProperty("basedir") + "/" + TEST_OUTPUT + RUN_NAME + "/screenshots/" + fileName + ".png";
    }
}

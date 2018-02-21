package com.billennium.testing.screenshooter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.billennium.testing.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static com.billennium.testing.Constants.RUN_NAME;
import static com.billennium.testing.Constants.TEST_OUTPUT;
import static com.billennium.testing.reporter.ReportManager.getTest;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

public class ScreenshotProvider {

    public synchronized static void takeScreenshot(String testName, boolean isSuccess, WebDriver driver) {

        String status;
        String path;

        try {
            if (isSuccess) {
                status = "Success";
            } else {
                status = "Failed";
            }
            path = generateFilePath(status, testName);
            takeScreenshot(path, driver);
            getTest().get().info(status, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (
                IOException e)
        {
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

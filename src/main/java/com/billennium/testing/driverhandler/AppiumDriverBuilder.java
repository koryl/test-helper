package com.billennium.testing.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.billennium.testing.Constants.APP_PATH;

public abstract class AppiumDriverBuilder<SELF, DRIVER extends AppiumDriver> {

    protected URL endpoint;

    public static AndroidDriverBuilder forAndroid() {

        return new AndroidDriverBuilder();
    }

    public static IOSDriverBuilder forIOS() {
        return new IOSDriverBuilder();
    }

    public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver> {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        public AndroidDriver build() {

            capabilities.setCapability("platformName", System.getProperty("platformName"));
            capabilities.setCapability("platformVersion", System.getProperty("platformVersion"));
            capabilities.setCapability("deviceName", System.getProperty("deviceName"));
            capabilities.setCapability("noReset", System.getProperty("noReset"));
            capabilities.setCapability("app", APP_PATH);

            return new AndroidDriver(endpoint, capabilities);
        }
    }

    public static class IOSDriverBuilder extends AppiumDriverBuilder<IOSDriverBuilder, IOSDriver> {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        public IOSDriver build() {

            capabilities.setCapability("platformName", System.getProperty("platformName"));
            capabilities.setCapability("platformVersion", System.getProperty("platformVersion"));
            capabilities.setCapability("deviceName", System.getProperty("deviceName"));
            capabilities.setCapability("udid", System.getProperty("udid"));
            capabilities.setCapability("bundleId", System.getProperty("bundleId"));
            capabilities.setCapability("automationName", System.getProperty("automationName"));

            return new IOSDriver(endpoint, capabilities);
        }
    }

    public SELF withEndpoint(URL endpoint) {
        this.endpoint = endpoint;

        return (SELF) this;
    }
}


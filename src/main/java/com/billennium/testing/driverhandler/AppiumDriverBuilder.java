package com.billennium.testing.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.billennium.testing.Constants.*;

/**
 * Class created to handle AppiumDriver instance.
 */
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

        /**
         * Builds AndroidDriver instance.
         *
         * @return AndroidDriver instance
         */
        public AndroidDriver build() {

            capabilities.setCapability("platformName", PLATFORM_NAME);
            capabilities.setCapability("platformVersion", PLATFORM_VERSION);
            capabilities.setCapability("deviceName", DEVICE_NAME);
            capabilities.setCapability("noReset", System.getProperty("noReset"));
            capabilities.setCapability("app", APP_PATH);

            return new AndroidDriver(endpoint, capabilities);
        }
    }

    public static class IOSDriverBuilder extends AppiumDriverBuilder<IOSDriverBuilder, IOSDriver> {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        /**
         * Builds IOSDriver instance.
         *
         * @return IOSDriver instance
         */
        public IOSDriver build() {

            capabilities.setCapability("platformName", PLATFORM_NAME);
            capabilities.setCapability("platformVersion", PLATFORM_VERSION);
            capabilities.setCapability("deviceName", DEVICE_NAME);
            capabilities.setCapability("udid", System.getProperty("udid"));
            capabilities.setCapability("bundleId", System.getProperty("bundleId"));
            capabilities.setCapability("automationName", System.getProperty("automationName"));

            return new IOSDriver(endpoint, capabilities);
        }
    }

    /**
     * Sets endpoint for Appium Server.
     *
     * @param endpoint URL where the Appium Server is running
     * @return
     */
    public SELF withEndpoint(URL endpoint) {
        this.endpoint = endpoint;

        return (SELF) this;
    }
}


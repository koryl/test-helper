package io.github.koryl.tah.driverhandler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.net.URL;

import static io.github.koryl.tah.Constants.DRIVER_PATH;

/**
 * Class created to handle WebDriver instance.
 */
public abstract class WebDriverBuilder<SELF, DRIVER extends WebDriver> {

    private URL endpoint;

    public static ChromeDriverBuilder forChrome() {
        return new ChromeDriverBuilder();
    }

    public static InternetExplorerDriverBuilder forIE() {
        return new InternetExplorerDriverBuilder();
    }

    public static class ChromeDriverBuilder extends WebDriverBuilder<ChromeDriverBuilder, ChromeDriver> {

        /**
         * Builds ChromeDriver instance.
         *
         * @return ChromeDriver instance
         */
        public ChromeDriver build() {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            return new ChromeDriver(options);
        }
    }

    public static class InternetExplorerDriverBuilder extends WebDriverBuilder<InternetExplorerDriverBuilder, InternetExplorerDriver> {

        public InternetExplorerDriver build() {

            return new InternetExplorerDriver();
        }
    }

    public SELF withEndpoint(URL endpoint) {
        this.endpoint = endpoint;

        return (SELF) this;
    }
}


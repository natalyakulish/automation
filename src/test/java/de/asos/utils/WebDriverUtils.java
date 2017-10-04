package de.asos.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class WebDriverUtils {
    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "/Users/natalyakulish/Downloads/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("test-type");
//        LoggingPreferences loggingPreferences = new LoggingPreferences();
//        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
//        loggingPreferences.enable(LogType.DRIVER, Level.ALL);
//        loggingPreferences.enable(LogType.CLIENT, Level.ALL);


        final DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setPlatform(Platform.MAC);
        chrome.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        chrome.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

        WebDriver driver = new ChromeDriver(chrome);
        driver.manage().deleteAllCookies();
        return driver;
    }
}

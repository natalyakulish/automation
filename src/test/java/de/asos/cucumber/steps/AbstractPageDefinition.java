package de.asos.cucumber.steps;

import de.asos.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbstractPageDefinition {

    protected static WebDriver driver;
    protected WebDriver getDriver(){
        if (driver == null){
            driver = WebDriverUtils.getNewChromeDriver();
        }
        return WebDriverUtils.getExistingChromeDriver();
    }
}

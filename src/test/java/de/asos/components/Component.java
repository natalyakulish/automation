package de.asos.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class Component {

    protected final WebDriver driver;

    public Component(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void timeoutSeconds(int howLong){
        driver.manage().timeouts().implicitlyWait(howLong, TimeUnit.SECONDS);
    }

    protected void waitFor(ExpectedCondition expectedCondition) {
        new FluentWait<WebDriver>(driver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(5, TimeUnit.SECONDS)
                .until(expectedCondition);
    }
}

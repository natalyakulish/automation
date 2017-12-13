package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        timeoutSeconds(5);
    }

    public Page() {

    }

    public void timeoutSeconds(int howlong) {
        driver.manage().timeouts().implicitlyWait(howlong, TimeUnit.SECONDS);
    }

    protected void waitForIgnoring(ExpectedCondition expectedCondition) {
        new FluentWait<WebDriver>(driver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(5, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(expectedCondition);
    }

    protected void waitForVisibility(By locator) {
        waitForIgnoring(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForPresence(By locator) {
        waitForIgnoring(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }


    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}


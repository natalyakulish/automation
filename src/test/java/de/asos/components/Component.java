package de.asos.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public void dragAndDrop2(WebElement element, int xOffset, int yOffset)
            throws Exception {
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.dragAndDropBy(element, xOffset, yOffset) .build();
        dragAndDrop.perform();
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
    protected void waitForPresence(By locator){
        waitForIgnoring(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

}

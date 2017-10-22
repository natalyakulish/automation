package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends Page {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsGreeting(String fullName) {
        waitForDisplayed(By.xpath("//nav[@data-auto-id=\"main-nav\"]"));
        return driver.findElement(By.xpath("//span[text()='" + fullName + "']")).isDisplayed();
    }
}

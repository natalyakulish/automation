package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class MyAccountPage extends Page {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsGreeting(String fullName) {
        waitForVisibility(By.xpath("//nav[@data-auto-id=\"main-nav\"]"));
        return driver.findElement(By.xpath("//span[text()='" + fullName + "']")).isDisplayed();
    }

    public MyOrdersPage openMyOrders() {
        waitForVisibility(By.xpath("//div/span[contains(text(), 'My orders')]"));
        driver.findElement(By.xpath("//div/span[contains(text(), 'My orders')]")).click();
        return new MyOrdersPage(driver);
    }

    public PremierDeliveryPage openPremierDelivery() {
        waitForVisibility(By.xpath("//li/a[@href='/my-account/premier-delivery']//span"));
        driver.findElement(By.xpath("//li/a[@href='/my-account/premier-delivery']//span")).click();

        return new PremierDeliveryPage(driver);
    }

    public MyDetailsPage openMyDetailsPage() {
        waitForVisibility(By.xpath("//div/span[contains(text(), 'My details')]"));
        driver.findElement(By.xpath("//div/span[contains(text(), 'My details')]")).click();

        return new MyDetailsPage(driver);
    }
}

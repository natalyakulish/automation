package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyDetailsPage extends Page{

    public MyDetailsPage(WebDriver driver){
        super(driver);
    }

    public MyDetailsPage editMyDetails(String newFirstName, String newLastName, String newEmail) {
        WebElement firstName = driver.findElement(By.xpath("//div[@id='MyDetails_firstName']//input"));
        firstName.clear();
        firstName.sendKeys(newFirstName);
        WebElement lastName = driver.findElement(By.xpath("//div[@id='MyDetails_lastName']//input"));
        lastName.clear();
        lastName.sendKeys(newLastName);
        WebElement email = driver.findElement(By.xpath("//div[@id='MyDetails_emailAddress']//input"));
        email.clear();
        email.sendKeys(newEmail);
        return null;
    }

    public String displayNotificationMessage() {
        waitForVisibility(By.xpath("//div[@data-auto-id='NotificationArea']//div"));
        WebElement element = driver.findElement(By.xpath("//div[@data-auto-id='NotificationArea']//div"));

        return element.getText();
    }

    public MyDetailsPage saveMyDetailsChanges() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return new MyDetailsPage(driver);
    }
}

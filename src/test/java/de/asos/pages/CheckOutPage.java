package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends Page {
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage selectDeliveryCountry(String country) {
        waitForPresence(By.id("countryCode"));
        driver.findElement(By.id("countryCode")).click();
        WebElement countryOption = driver.findElement(By.xpath("//div/select[@id='countryCode']/option[@value='" + country + "']"));
        countryOption.click();

        return new CheckOutPage(driver);
    }

    public CheckOutPage selectMobileNumber(String mobileNumber) {
        driver.findElement(By.id("contactNumber")).sendKeys(mobileNumber);
        return new CheckOutPage(driver);
    }

    public CheckOutPage selectAddress(String address) {
        WebElement addressStreet = driver.findElement(By.id("address1"));
        addressStreet.sendKeys(address);
        WebElement addressCity = driver.findElement(By.xpath("//div/input[@id='locality']"));
        addressCity.sendKeys("Munich");
        WebElement postalCode = driver.findElement(By.xpath("//div/input[@id='postalCode']"));
        postalCode.sendKeys("817373");
        return new CheckOutPage(driver);
    }

    public CheckOutPage saveDeliveryAddress() {
        WebElement button = driver.findElement(By.xpath("//div/button[@class='btn primary save-address']"));
        button.click();
        return new CheckOutPage(driver);
    }
}

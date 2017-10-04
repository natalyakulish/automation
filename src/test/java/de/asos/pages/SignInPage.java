package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class SignInPage extends Page{

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public SignInPage openRegistrationForm() {
        driver.findElement(By.xpath("//div/a[@class='qa-join-asos']")).click();
        return new SignInPage(driver);
    }

    public HomePage registrationByEmail() {

        this.driver.findElement(By.id("Email")).sendKeys("natalyakulish@gmail.com");
        this.driver.findElement(By.id("FirstName")).sendKeys("Natalie");
        this.driver.findElement(By.id("LastName")).sendKeys("Kulish");
        this.driver.findElement(By.id("Password")).sendKeys("margar1ta27");
        timeoutSeconds(3);
        this.driver.findElement(By.xpath("//div//option[text()='27']")).click();
        this.driver.findElement(By.xpath("//div//option[text()='October']")).click();
        this.driver.findElement(By.xpath("//div//option[text()='1989']")).click();
        this.driver.findElement(By.xpath("//*[@id='female']/..")).click();
        timeoutSeconds(3);
        this.driver.findElement(By.xpath("//div/label[@class='checkbox qa-marketing-label']")).click();
        this.driver.findElement(By.xpath("//div/label[@class='checkbox']")).click();
        this.driver.findElement(By.id("register")).click();

        return new HomePage(driver);
    }

    public HomePage signIn(String email, String password) {

        driver.findElement(By.id("EmailAddress")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("signin")).click();

        return new HomePage(driver);
    }
}

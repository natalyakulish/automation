package de.asos.pages;

import de.asos.models.UserDataModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public HomePage registrationByEmail(UserDataModel userDataModel) {
        driver.findElement(By.id("Email")).sendKeys(userDataModel.getEmail());
        driver.findElement(By.id("FirstName")).sendKeys(userDataModel.getFirstName());
        driver.findElement(By.id("LastName")).sendKeys(userDataModel.getLastName());
        driver.findElement(By.id("Password")).sendKeys(userDataModel.getPassword());
        driver.findElement(By.xpath("//div//option[text()='" + userDataModel.getDate() + "']")).click();
        driver.findElement(By.xpath("//div//option[text()='" + userDataModel.getMonth() + "']")).click();
        driver.findElement(By.xpath("//div//option[text()='" + userDataModel.getYear() + "']")).click();
        driver.findElement(By.xpath("//*[@id='" + userDataModel.getSex() + "']/..")).click();
        driver.findElement(By.xpath("//div/label[@class='checkbox qa-marketing-label']")).click();
        driver.findElement(By.xpath("//div/label[@class='checkbox']")).click();
        driver.findElement(By.id("register")).click();

        return new HomePage(driver);
    }

    public HomePage signIn(UserDataModel userDataModel) {

        driver.findElement(By.id("EmailAddress")).sendKeys(userDataModel.getEmail());
        driver.findElement(By.id("Password")).sendKeys(userDataModel.getPassword());
        driver.findElement(By.id("signin")).click();

        return new HomePage(driver);
    }

    public HomePage signIn(String email, String password) {

        driver.findElement(By.id("EmailAddress")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("signin")).click();

        return new HomePage(driver);
    }
}

package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


/**
 * Created by natalyakulish on 29.09.17.
 */
public class HomePage extends Page {

    @FindBy(linkText = "Sign In")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@id='signedin']//a[@class='sign-out']")
    private WebElement logOutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignInPage openSignInPage() {
        signInLink.click();
        return new SignInPage(driver);
    }

    public boolean hasSignOutLink() {
        return logOutLink.isDisplayed();
    }

    public CategoryPage openCategory(String sex, String category) {

        Actions builder = new Actions(driver);

        WebElement sexElement = driver.findElement(By.xpath("//nav//li//span[text()='" + sex + "']"));
        WebElement categoryElement = driver.findElement(By.xpath("//div[@class='sub-floor-menu']//ul/li/a[text()='" + category + "']"));
        builder.moveToElement(sexElement)
                .click(categoryElement)
                .build()
                .perform();
        return new CategoryPage(driver);
    }

    public HomePage signOut() {
        if (logOutLink.isDisplayed()) {
            System.out.println("Is displayed");
            logOutLink.click();
        }

        return new HomePage(driver);

    }

    public boolean hasSignInLink() {
        return signInLink.isDisplayed();
    }
}

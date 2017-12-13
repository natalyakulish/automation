package de.asos.pages;

import de.asos.constants.PathConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class HomePage extends Page {

    @FindBy(linkText = "Sign In")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@id='signedin']//a[@class='sign-out']")
    private WebElement signOutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean hasSignOutLink() {
        return signOutLink.isDisplayed();
    }

    public boolean hasSignInLink() {
        return signInLink.isDisplayed();
    }

    public HomePage signOut() {
        signOutLink.click();
        return new HomePage(driver);
    }

    public SignInPage openSignInPage() {
        signInLink.click();
        return new SignInPage(driver);
    }

    public MyAccountPage openMyAccountPage() {
        driver.findElement(By.id("my-account-link")).click();
        return new MyAccountPage(driver);
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

    public HomePage refreshPage() {
        WebElement element = driver.findElement(By.xpath("//div[@id='signedin']//a[@class='sign-out']"));
                element.sendKeys(Keys.F5);
        driver.navigate().refresh();
        timeoutSeconds(2);
        return new HomePage(driver);

    }

    public void openHelpPage(String link) {

        List<WebElement> linksList = driver.findElements(By.xpath("//ul[@class='my-details']/li/a"));
        for (WebElement linkInTheList : linksList){
            String linkInTheListText = linkInTheList.getText();
            if (linkInTheListText.equals(link)){
                linkInTheList.click();
                break;
            }else {
                throw new IllegalArgumentException(linkInTheList + "is not a valid link");
            }
        }

    }

    public BagPage openBagPage() {
        driver.findElement(By.xpath("//li[@id='miniBagApp']/div")).click();
            return new BagPage(driver);
    }
}

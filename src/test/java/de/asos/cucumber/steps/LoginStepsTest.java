package de.asos.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.ro.Si;
import de.asos.constants.PathConstants;
import de.asos.pages.HomePage;
import de.asos.pages.MyAccountPage;
import de.asos.pages.SignInPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

public class LoginStepsTest {

    private WebDriver driver;

    public LoginStepsTest() {
        driver = WebDriverUtils.getExistingChromeDriver();
    }



    @Then("^he can see \"([^\"]*)\" account name$")
    public void he_can_see_account_name(String accountFullName) throws Throwable {
        MyAccountPage myAccountPage = new MyAccountPage(driver);//to check
        if (!myAccountPage.containsGreeting(accountFullName)) {
            throw new Exception("Does not contain");
        }
    }

    @Then("^url must contain \"([^\"]*)\" string$")
    public void url_must_contain_string(String urlString) throws Throwable {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(urlString));
    }

    }



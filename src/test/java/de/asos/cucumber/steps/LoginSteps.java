package de.asos.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.asos.constants.PathConstants;
import de.asos.pages.HomePage;
import de.asos.pages.MyAccountPage;
import de.asos.pages.SignInPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private HomePage homePage;
    private WebDriver driver;
    private MyAccountPage myAccountPage;

    public LoginSteps() {
        driver = WebDriverUtils.getExistingChromeDriver();
    }

    @Given("^User is on \"([^\"]*)\" page$")
    public void user_is_on_page(String pageName) throws Throwable {
        if ("home".equals(pageName)) {
            driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
            homePage = new HomePage(driver);
        } else {
            throw new InvalidArgumentException(pageName + " is not a valid page name.");
        }
    }

    @When("^he tries to login with \"([^\"]*)\" email and \"([^\"]*)\" password$")
    public void he_tries_to_login_with_email_and_password(String email, String password) throws Throwable {
        SignInPage signInPage = homePage.openSignInPage();
        homePage = signInPage.signIn(email, password);
    }

    @When("^he opens \"([^\"]*)\" page$")
    public void he_opens_page(String pageName) throws Throwable {
        if ("my-account".equals(pageName)) {
            myAccountPage = homePage.openMyAccountPage();
        } else {
            throw new InvalidArgumentException(pageName + " is not a valid page name");
        }
    }

    @Then("^he can see \"([^\"]*)\" account name$")
    public void he_can_see_account_name(String accountFullName) throws Throwable {
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

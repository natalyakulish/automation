package de.asos.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.asos.factory.UserDataFactory;
import de.asos.models.UserDataModel;
import de.asos.pages.HomePage;
import de.asos.pages.MyAccountPage;
import de.asos.pages.SignInPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class RegistrationCucumberTest {
    private WebDriver driver = WebDriverUtils.getExistingChromeDriver();

    @When("^User opens \"([^\"]*)\" form$")
    public void user_opens_form(String formName) throws Throwable {
        if (formName.equals("Registration")) {
            SignInPage signInPage = new SignInPage(driver);
            signInPage.openRegistrationForm();
        } else {
            throw new IllegalArgumentException(formName + " this form is invalid");
        }
    }

    @When("^User inputs \"([^\"]*)\"$")
    public void user_inputs(String registrationData) throws Throwable {
        if (registrationData.equals("Valid registration data")) {

            UserDataModel userDataModel = UserDataFactory.getUserData();
            SignInPage registration = new SignInPage(driver);
            registration.registrationByEmail(userDataModel);

        }
    }

    @Then("^Page contains Users \"([^\"]*)\" from \"([^\"]*)\"$")
    public void page_contains_Users_from(String loggedInUserData, String registrationData) throws Throwable {
        if (registrationData.equals("Valid registration data") && loggedInUserData.equals("full name")) {
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            UserDataModel userDataModel = UserDataFactory.getUserData();
            String fullName = userDataModel.getFullName();
            Assert.assertTrue(myAccountPage.containsGreeting(fullName));
        } else {
            throw new IllegalArgumentException(registrationData + " or " + loggedInUserData + " are not valid");
        }
    }
}

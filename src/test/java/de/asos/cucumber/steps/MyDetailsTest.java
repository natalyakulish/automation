package de.asos.cucumber.steps;

import cucumber.api.java.en.When;
import de.asos.pages.MyDetailsPage;
import de.asos.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;

public class MyDetailsTest {
   public WebDriver driver = WebDriverUtils.getExistingChromeDriver();

    @When("^User inputs \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_inputs_and_and(String firstName, String lastName, String email) throws Throwable {
        MyDetailsPage myDetailsPage = new MyDetailsPage(driver);
        myDetailsPage.editMyDetails(firstName,lastName,email);
    }
}

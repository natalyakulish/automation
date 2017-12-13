package de.asos.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.asos.pages.BagPage;
import de.asos.pages.CheckOutPage;
import de.asos.pages.HomePage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LogOutCucumberTest {

    private WebDriver driver = WebDriverUtils.getExistingChromeDriver();

    @When("^User refresh the page$")
    public void user_refresh_the_page() throws Throwable {
        HomePage homePage = new HomePage(driver);
        HomePage homePageAfterRefresh = homePage.refreshPage();
    }


    @Then("^\"([^\"]*)\" page has \"([^\"]*)\" link$")
    public void page_has_link(String pageName, String link) throws Throwable {
       if (pageName.equals("Home")){
           if (link.equals("Sign in")) {
               HomePage homepageAfterLogout = new HomePage(driver);
               Assert.assertTrue(homepageAfterLogout.hasSignInLink());
           }else {
               throw new IllegalArgumentException(pageName + " or " + link + " this links are wrong");
           }
       }
    }
}

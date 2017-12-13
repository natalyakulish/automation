package de.asos.cucumber.steps;

import cucumber.api.java.en.Then;
import de.asos.pages.PremierDeliveryPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PremierDeliveryTest {
     private WebDriver driver = WebDriverUtils.getExistingChromeDriver();


    @Then("^delivery was added to Bag$")
    public void delivery_was_added_to_Bag() throws Throwable {
        PremierDeliveryPage premierDeliveryPage = new PremierDeliveryPage(driver);
       Assert.assertTrue(premierDeliveryPage.getAddedTick());
    }
}

package de.asos.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import de.asos.pages.BagPage;
import de.asos.pages.CheckOutPage;
import de.asos.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;

import static de.asos.cucumber.steps.AbstractPageDefinition.driver;

public class CheckOutProductTest {
    WebDriver driver;
    public CheckOutProductTest(){
        driver = WebDriverUtils.getExistingChromeDriver();
    }

    @When("^User makes CheckOut product with \"([^\"]*)\" type$")
    public void user_make_CheckOut_product_with_type(String delivery) throws Throwable {
        if (delivery.equals("Standard Delivery (Free)")) {
            BagPage bagPage = new BagPage(driver);
            bagPage.makeCheckoutofTheProduct(delivery);
        }else {
            throw new IllegalArgumentException("invalid delivery dropdown");
        }
    }
    @When("^User selects Delivery \"([^\"]*)\"$")
    public void user_selects_Delivery(String country) throws Throwable {
     CheckOutPage checkOutPage = new CheckOutPage(driver);
     checkOutPage.selectDeliveryCountry(country);
    }


    @When("^User inputs \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_inputs_and(String mobileNumber, String address) throws Throwable {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.selectMobileNumber(mobileNumber);
        checkOutPage.selectAddress(address);

    }

}

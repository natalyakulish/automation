package de.asos.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.asos.constants.PathConstants;
import de.asos.pages.*;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CommonStepsDefinition {
    private WebDriver driver;
    private HomePage homePage;

    public CommonStepsDefinition() {

        driver = WebDriverUtils.getExistingChromeDriver();
    }

    @Given("^User is on \"([^\"]*)\" page$")
    public void user_on_page(String pageName) throws Throwable {
        if ("Home".equals(pageName)) {
            driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
            homePage = new HomePage(driver);
        } else if ("Help".equals(pageName)) {
            driver.get("http://www.asos.com/customer-service/customer-care/help/?help=/app/home&CTARef=Header|Help");
        } else if ("HMHome".equals(pageName)) {
            driver.get("http://www.hm.com/de");
        } else {
            throw new InvalidArgumentException(pageName + " is not a valid page name.");
        }
    }

    @When("^User clicks on \"([^\"]*)\" link$")
    public void user_clicks_on_link(String link) throws Throwable {
        if (link.equals("Sign In")) {
            HomePage homePage = new HomePage(driver);
            homePage.openSignInPage();
        } else if (link.equals("My account")) {
            HomePage homePage = new HomePage(driver);
            homePage.openMyAccountPage();
        } else if (link.equals("Help")) {
            HomePage homePage = new HomePage(driver);
            homePage.openHelpPage(link);
        } else if (link.equals("Bag")) {
            HomePage homePage = new HomePage(driver);
            homePage.openBagPage();
        } else if (link.equals("My orders")) {
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.openMyOrders();
        } else if (link.equals("Premier delivery")){
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.openPremierDelivery();
        }else if (link.equals("My details")){
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            myAccountPage.openMyDetailsPage();
        }
        else {
            throw new IllegalArgumentException(link + " This link is not valid. Go home!");
        }
    }


    @When("^User navigates to \"([^\"]*)\" and selects \"([^\"]*)\"$")
    public void user_navigates_to_and_selects(String category, String subCategory) throws Throwable {
        if (category.equals("WOMEN")) {
            HomePage homePage = new HomePage(driver);
            homePage.openCategory(category, subCategory);
        } else if (category.equals("ORDER ISSUES")) {
            HelpPage helpPage = new HelpPage(driver);
            helpPage.openHelpCategory(category, subCategory);
        }else if (category.equals("DELIVERY")) {
            HelpPage helpPage = new HelpPage(driver);
            helpPage.openHelpCategory(category, subCategory);
        }else if (category.equals("RETURNS & REFUNDS")){
            HelpPage helpPage = new HelpPage(driver);
            helpPage.openHelpCategory(category,subCategory);
        }else {
            throw new IllegalArgumentException(category + " or " + subCategory + " are not valid");
        }
    }
    @Then("^User was check breadcrumbs that contains \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_was_check_breadcrumbs_that_contains_and(String category, String subCategory) throws Throwable {
        if (category.equals("WOMEN")){

            CategoryPage categoryPage = new CategoryPage(driver);
            Assert.assertTrue(categoryPage.containsBreadcrumbsItems(category, subCategory));
        }if (category.equals("Order Issues ")){
            HelpPage helpPage = new HelpPage(driver);
            Assert.assertTrue(helpPage.containsBreadCrumbsItems(category, subCategory));
        }if (category.equals(" Where's my order? ")){
            HelpPage helpPage = new HelpPage(driver);
            Assert.assertTrue(helpPage.containsBreadCrumbsItems(category, subCategory));
        }if (category.equals("Can I exchange?")){
            HelpPage helpPage = new HelpPage(driver);
            Assert.assertTrue(helpPage.containsBreadCrumbsItems(category, subCategory));
        }
    }
    @Then("^User checks page \"([^\"]*)\"$")
    public void user_check_page(String title) throws Throwable {
        if (title.equals("Shopping Bag \\| ASOS ")){
            BagPage bagPage = new BagPage(driver);
        }else if (title.equals("Women's Clothes")){
                HomePage homePage = new HomePage(driver);
        }else {
            throw new IllegalArgumentException(title + " is not correct");
        }

    }

    @When("^User clicks \"([^\"]*)\" button$")
    public void user_click_button(String buttonName) throws Throwable {
        if (buttonName.equals("Sign Out")) {
            HomePage homePageAfterLogout = new HomePage(driver);
            homePageAfterLogout.signOut();
        }else if (buttonName.equals("Check Out")) {
            BagPage bagPage = new BagPage(driver);
            bagPage.checkOut();
        }else if (buttonName.equals("Deliver to this address")) {
                CheckOutPage checkOutPage = new CheckOutPage(driver);
                checkOutPage.saveDeliveryAddress();
        }else if (buttonName.equals("Start shopping")){
            MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
            myOrdersPage.goShopping();
        }else if (buttonName.equals("Find out more")){
            PremierDeliveryPage premierDeliveryPage = new PremierDeliveryPage(driver);
            premierDeliveryPage.SignUpToPremierDelivery();
        }else if (buttonName.equals("Add to bag")){
            PremierDeliveryPage premierDeliveryPage = new PremierDeliveryPage(driver);
            premierDeliveryPage.addPremierDeliveryToBag();
        }else if (buttonName.equals("Save changes")){
            MyDetailsPage myDetailsPage = new MyDetailsPage(driver);
            myDetailsPage.saveMyDetailsChanges();

        }
        else {
            throw new IllegalArgumentException(buttonName + " is isnavlid button");
        }
    }
    @When("^he tries to login with \"([^\"]*)\" email and \"([^\"]*)\" password$")
    public void user_tries_to_login_with_email_and_password(String email, String password) throws Throwable {
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = homePage.openSignInPage();
        homePage = signInPage.signIn(email, password);
    }

    @When("^he opens \"([^\"]*)\" page$")
    public void he_opens_page(String pageName) throws Throwable {
        if ("my-account".equals(pageName)) {
            HomePage homePage = new HomePage(driver);//to check
            MyAccountPage myAccountPage = new MyAccountPage(driver);//to check
            myAccountPage = homePage.openMyAccountPage();
        } else {
            throw new InvalidArgumentException(pageName + " is not a valid page name");
        }
    }
    @Then("^Page url contains \"([^\"]*)\"$")
    public void page_url_contains(String url) throws Throwable {
        Assert.assertTrue( driver.getCurrentUrl().contains(url));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/my-account/premier-delivery")) {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles.remove(driver.getWindowHandle());
            String[] toArray = windowHandles.toArray(new String[0]);

            if (toArray.length > 0) {
                driver.close();
                driver.switchTo().window(toArray[0]);
            }

        }
    }
    @Then("^Error Message displayed \"([^\"]*)\"$")
    public void error_Message_displayed(String message) throws Throwable {
        if (message.equals("Looks like either your email address or password were incorrect. Wanna try again?")) {
            SignInPage signInPage = new SignInPage(driver);
            String signInPageAfterError = signInPage.displayErrorMassage();

            Assert.assertTrue(signInPageAfterError.equals(message));
        }else if (message.equals("It looks like you already have Premier Delivery in your bag – enjoy!")){
            PremierDeliveryPage premierDeliveryPage = new PremierDeliveryPage(driver);
            premierDeliveryPage.displayErrorMassage();
        }
    }
    @Then("^Notification message displayed \"([^\"]*)\"$")
    public void notification_message_displayed(String notification) throws Throwable {
      MyDetailsPage myDetailsPage = new MyDetailsPage(driver);
      String detailsWithNotification = myDetailsPage.displayNotificationMessage();

      Assert.assertTrue(detailsWithNotification.equals(notification));
    }
}
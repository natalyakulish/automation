package de.asos.cucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.asos.components.FilterComponent;
import de.asos.constants.PathConstants;
import de.asos.pages.CategoryPage;
import de.asos.pages.HomePage;
import de.asos.pages.ProductDetailsPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FilterProductsCucumerTest {
    private WebDriver driver;
    private CategoryPage categoryPage;
    private FilterComponent filter;
    private ProductDetailsPage productDetailsPage;

    public FilterProductsCucumerTest() {
        driver = WebDriverUtils.getExistingChromeDriver();
    }

    @And("^User checks first enabled checkbox in all filters$")
    public void user_checks_first_enabled_checkbox_in_all_filters() throws Throwable {
        CategoryPage categoryPage = new CategoryPage(driver);
        filter = categoryPage.getFilter();
        filter.checkFirstEnabledCheckboxInAllCategories();
    }

    @Then("^User checks number of checked checkboxes$")
    public void user_checks_number_of_checked_checkboxes() throws Throwable {
        categoryPage = new CategoryPage(driver);
        Assert.assertEquals(filter.findCheckedCheckboxes(), filter.findPanelsWithCheckboxes());
    }

    @And("^User checks the product with (\\d+)$")
    public ProductDetailsPage user_checks_the_product_with(int index) throws Throwable {
        WebElement element = driver.findElement(By.xpath("id('productlist-results')//div[contains(@class,'results')]//li[" + index + "]"));
        element.click();
        return new ProductDetailsPage(driver);

    }

    @And("^User selects size of product$")
    public ProductDetailsPage user_selects_size_of_product() throws Throwable {
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.selectFirstAvailableSize();
        return new ProductDetailsPage(driver);
    }

    @Then("^add product to Bag$")
    public ProductDetailsPage add_product_to_Bag() throws Throwable {
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.addToBag();
        return new ProductDetailsPage(driver);
    }

    @Then("^products was added to Bag$")
    public void proudcts_was_added_to_Bag() throws Throwable {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.hasMinibagOverlayContainer());
    }

}


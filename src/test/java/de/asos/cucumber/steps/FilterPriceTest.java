package de.asos.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import de.asos.components.FilterComponent;
import de.asos.pages.CategoryPage;
import de.asos.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class FilterPriceTest {

    WebDriver driver;
    public FilterPriceTest(){
        driver = WebDriverUtils.getExistingChromeDriver();
    }

    @Then("^User move Price range slider to the \"([^\"]*)\" left and \"([^\"]*)\" right$")
    public void user_move_Price_range_slider_to_the_left_and_right(int left, int right) throws Throwable {

        CategoryPage categoryPage = new CategoryPage(driver);
        FilterComponent filter = new FilterComponent(driver);
        filter = categoryPage.getFilter();
        filter.checkProductPrice(left,right);


    }

}

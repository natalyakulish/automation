package de.asos.tests;

import de.asos.components.FilterComponent;
import de.asos.pages.CategoryPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SortByPriceTest {
    
    private WebDriver chromeDriver;

    @Before
    public void setUp() throws Exception {
        chromeDriver = WebDriverUtils.getChromeDriver();
        chromeDriver.get("http://www.asos.com/women/shoes/cat/?cid=4172");
    }


    @Test
    public void sortByPrice() throws Exception {
        CategoryPage categoryPage = new CategoryPage(chromeDriver);
        FilterComponent filter = categoryPage.getFilter();
        filter.filterByPrice(80,100);

        Assert.assertEquals(filter.getSelectedMinPrice(), 80);
        Assert.assertEquals(filter.getSelectedMaxPrice(), 100);


    }
}

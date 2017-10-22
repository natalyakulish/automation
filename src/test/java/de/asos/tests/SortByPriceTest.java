package de.asos.tests;

import de.asos.components.FilterComponent;
import de.asos.pages.CategoryPage;
import org.junit.Assert;

public class SortByPriceTest extends BaseTest{
    
    protected void preconditions() throws Exception {
        driver.get("http://www.asos.com/women/shoes/cat/?cid=4172");
    }

    public void sortByPrice() throws Exception { //TODO implement
        CategoryPage categoryPage = new CategoryPage(driver);
        FilterComponent filter = categoryPage.getFilter();
        filter.filterByPrice(80,100);

        Assert.assertEquals(filter.getSelectedMinPrice(), 80);
        Assert.assertEquals(filter.getSelectedMaxPrice(), 100);
    }
}

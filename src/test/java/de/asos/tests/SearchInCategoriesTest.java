package de.asos.tests;

import de.asos.constants.PathConstants;
import de.asos.pages.CategoryPage;
import de.asos.pages.HomePage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SearchInCategoriesTest {

    private WebDriver chromeDriver;

    @Before
    public void setUp() throws Exception {


        chromeDriver = WebDriverUtils.getChromeDriver();
        chromeDriver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Test
    public void searchInCategories() throws Exception {

        HomePage homePage = new HomePage(chromeDriver);
        homePage.timeoutSeconds(1);

        String sexCategory = "WOMEN";
        String category = "Shoes";
        CategoryPage categoryPage = homePage.openCategory(sexCategory, category);
        homePage.timeoutSeconds(1);

        Assert.assertTrue(categoryPage.containsBreadcrumbsItems("Women", category));



    }
}

package de.asos.tests;

import de.asos.components.FilterComponent;
import de.asos.constants.PathConstants;
import de.asos.pages.CategoryPage;
import de.asos.pages.HomePage;
import de.asos.pages.ProductDetailsPage;
import org.junit.Assert;
import org.junit.Test;

public class FilterProductsTest extends BaseTest {

    protected void preconditions() throws Exception {
        driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Test
    public void filterProducts() throws Exception {
        HomePage homePage = new HomePage(driver);

        String category = "Shoes";
        CategoryPage categoryPage = homePage.openCategory("WOMEN", category);

        Assert.assertTrue(categoryPage.containsBreadcrumbsItems("Women", category));

        FilterComponent filter = categoryPage.getFilter();
        filter.checkFirstEnabledCheckboxInAllCategories();

        Assert.assertEquals(filter.findCheckedCheckboxes(), filter.findPanelsWithCheckboxes());

        ProductDetailsPage productDetailsPage = categoryPage.clickOnFirstProduct(1);
        productDetailsPage.selectFirstAvailableSize().addToBag();

        Assert.assertTrue(productDetailsPage.hasMinibagOverlayContainer());
    }

}

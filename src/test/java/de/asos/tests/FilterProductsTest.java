package de.asos.tests;

import de.asos.components.FilterComponent;
import de.asos.constants.PathConstants;
import de.asos.pages.CategoryPage;
import de.asos.pages.HomePage;
import de.asos.utils.WebDriverUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;

import java.util.Set;

public class FilterProductsTest {

    private WebDriver chromeDriver;

    @Before
    public void setUp() throws Exception {

        chromeDriver = WebDriverUtils.getChromeDriver();
        chromeDriver.get(PathConstants.ASOS_HOME_PAGE_PATH);
//        chromeDriver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Test
    public void filterProducts() throws Exception {

        HomePage homePage = new HomePage(chromeDriver);
        homePage.timeoutSeconds(1);

//        SignInPage signInPage = homePage.openSignInPage();
//        signInPage.timeoutSeconds(1);
//
//        HomePage homepageAfterLogin = signInPage.signIn("natalyakulish@gmail.com","margar1ta27");
//        homepageAfterLogin.timeoutSeconds(1);

//        CategoryPage categoryPage = homePage.openCategory("WOMEN", "Shoes");
//        categoryPage.timeoutSeconds(1);

        CategoryPage categoryPage = new CategoryPage(chromeDriver);
        FilterComponent filter = categoryPage.getFilter();
        FilterComponent filterComponent = filter.filterCategory("brand", "Adidas");
        filter.timeoutSeconds(3);
        FilterComponent filterComponentSize = filterComponent.filterCategory("size", "S");
        filterComponentSize.timeoutSeconds(1);



    }

//    @After
    public void tearDown() throws Exception {
        Logs logs = chromeDriver.manage().logs();
        Set<String> availableLogTypes = logs.getAvailableLogTypes();
        for (String availableLogType : availableLogTypes) {
            for (LogEntry logEntry : logs.get(availableLogType)) {
                System.out.println("(" + availableLogType + ") " + logEntry.getLevel() + ": " + logEntry.getMessage());
            }
        }
    }
}

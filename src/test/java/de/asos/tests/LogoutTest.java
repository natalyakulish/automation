package de.asos.tests;

import de.asos.constants.PathConstants;
import de.asos.pages.HomePage;
import de.asos.pages.SignInPage;
import de.asos.utils.WebDriverUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LogoutTest {

    private WebDriver chromeDriver;

    @Before
    public void setUp() throws Exception {

        chromeDriver = WebDriverUtils.getChromeDriver();
        chromeDriver.get(PathConstants.ASOS_HOME_PAGE_PATH);

    }

    @Test
    public void logout() throws Exception {
        HomePage homePage = new HomePage(chromeDriver);
        homePage.timeoutSeconds(1);
        homePage.hasSignInLink();

        SignInPage signInPage = homePage.openSignInPage();
        signInPage.timeoutSeconds(1);

        HomePage homepageAfterLogout = signInPage.signIn("natalyakulish@gmail.com", "margar1ta27");
        signInPage.timeoutSeconds(1);

        homepageAfterLogout.signOut();

        Assert.assertTrue(homepageAfterLogout.hasSignInLink());




    }
}

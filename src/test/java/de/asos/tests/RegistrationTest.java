package de.asos.tests;


import de.asos.constants.PathConstants;
import de.asos.pages.HomePage;
import de.asos.pages.SignInPage;
import de.asos.utils.WebDriverUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class RegistrationTest {

    public WebDriver driver;

    @Before
    public void setUp() {
       this.driver = WebDriverUtils.getChromeDriver();
       driver.get(PathConstants.ASOS_HOME_PAGE_PATH);

    }

    @Test
    public void registrationOnAsos() {

        HomePage homePage = new HomePage(driver);
        homePage.timeoutSeconds(1);

        SignInPage signInPage = homePage.openSignInPage();
        signInPage.timeoutSeconds(1);

        SignInPage registration = signInPage.openRegistrationForm();
        registration.timeoutSeconds(1);

        HomePage homePageAfterRegistration = registration.registrationByEmail();
        homePageAfterRegistration.timeoutSeconds(1);


        Assert.assertEquals(true, homePageAfterRegistration.hasSignOutLink());
    }

    @After
    public void tearDown() throws Exception {

    }
}

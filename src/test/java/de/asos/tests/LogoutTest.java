package de.asos.tests;

import de.asos.constants.PathConstants;
import de.asos.factory.UserDataFactory;
import de.asos.models.UserDataModel;
import de.asos.pages.HomePage;
import de.asos.pages.SignInPage;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LogoutTest extends BaseTest {

    protected void preconditions() throws Exception {
        driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Ignore("Found bug: Header on Home Page does not have 'Log out' link right after login, " +
            "only after refresh of the page.")
    @Test
    public void logout() throws Exception {
        HomePage homePage = new HomePage(driver);

        SignInPage signInPage = homePage.openSignInPage();

        UserDataModel userData = UserDataFactory.getUserData();

        HomePage homepageAfterLogout = signInPage.signIn(userData);

        homepageAfterLogout.signOut();
        Assert.assertTrue(homepageAfterLogout.hasSignInLink());
    }
}

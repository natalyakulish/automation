package de.asos.tests;

import de.asos.constants.PathConstants;
import de.asos.factory.UserDataFactory;
import de.asos.models.UserDataModel;
import de.asos.pages.HomePage;
import de.asos.pages.MyAccountPage;
import de.asos.pages.SignInPage;
import org.junit.Assert;
import org.junit.Test;

public class SignInTest extends BaseTest{

    protected void preconditions() throws Exception {
        driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Test
    public void signIn() throws Exception {
        HomePage homePage = new HomePage(driver);

        SignInPage signInPage = homePage.openSignInPage();
        UserDataModel userData = UserDataFactory.getExistingUserData();

        HomePage homePageAfterSignIn = signInPage.signIn(userData);

        MyAccountPage myAccountPage = homePageAfterSignIn.openMyAccountPage();

        String fullName = userData.getFullName();

        Assert.assertTrue(myAccountPage.getPageUrl().contains("my-account"));
        Assert.assertTrue(myAccountPage.containsGreeting(fullName));
    }
}

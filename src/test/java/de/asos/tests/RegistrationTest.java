package de.asos.tests;


import de.asos.constants.PathConstants;
import de.asos.factory.UserDataFactory;
import de.asos.models.UserDataModel;
import de.asos.pages.HomePage;
import de.asos.pages.MyAccountPage;
import de.asos.pages.SignInPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class RegistrationTest extends BaseTest{

    protected void preconditions() throws Exception {
        driver.get(PathConstants.ASOS_HOME_PAGE_PATH);
    }

    @Test
    public void registrationOnAsos() {
        HomePage homePage = new HomePage(driver);

        SignInPage signInPage = homePage.openSignInPage();
        SignInPage registration = signInPage.openRegistrationForm();

        UserDataModel userDataModel = UserDataFactory.getUserData();
        String fullName = userDataModel.getFullName();

        HomePage homePageAfterRegistration = registration.registrationByEmail(userDataModel);

        MyAccountPage myAccountPage = homePageAfterRegistration.openMyAccountPage();

        Assert.assertTrue(myAccountPage.getPageUrl().contains("my-account"));
        Assert.assertTrue(myAccountPage.containsGreeting(fullName));
    }

}

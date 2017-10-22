package de.asos.tests;

import de.asos.utils.WebDriverUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    /**
     * Overwrite for custom setup
     */
    protected abstract void preconditions() throws Exception;

    @Before
    public void setUp() throws Exception {
        driver = WebDriverUtils.getNewChromeDriver();
        preconditions();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
}

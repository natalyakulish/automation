package de.asos.cucumber;

import cucumber.api.java.After;
import de.asos.utils.WebDriverUtils;

public class Hooks {

    @After
    public void tearDown() throws Exception {
        WebDriverUtils.closeDriver();
    }

}

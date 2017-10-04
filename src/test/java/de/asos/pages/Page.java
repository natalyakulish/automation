package de.asos.pages;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by natalyakulish on 29.09.17.
 */
public class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void timeoutSeconds(int howlong){
        driver.manage().timeouts().implicitlyWait(howlong,TimeUnit.SECONDS);
    }

    public void scrollToElement(WebElement element) {

    }
}


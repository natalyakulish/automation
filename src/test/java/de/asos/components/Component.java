package de.asos.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Component {

    protected final WebDriver driver;

    public Component(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void timeoutSeconds(int howlong){
        driver.manage().timeouts().implicitlyWait(howlong, TimeUnit.SECONDS);
    }

    public void timeoutMilliseconds(int howlong){
        driver.manage().timeouts().implicitlyWait(howlong, TimeUnit.MILLISECONDS);
    }


}

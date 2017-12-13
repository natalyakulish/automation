package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyOrdersPage extends Page{
    public MyOrdersPage(WebDriver driver) {
        super(driver);
    }
    public HomePage goShopping() {
        driver.findElement(By.xpath("//div[@class='undefined']/a")).click();
        return new HomePage(driver);
    }
}

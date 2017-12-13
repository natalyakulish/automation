package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BagPage extends Page {

    public BagPage(WebDriver driver) {
        super(driver);

    }

    public BagPage makeCheckoutofTheProduct(String delivery) {
        waitForPresence(By.xpath("//div[@class='delivery-dropdown-holder']"));

        List<WebElement> checkOutOptions = driver.findElements(By.xpath("//span[@class='bag-item-selector--desktop']//option"));
        System.out.println("checkOutOptions = " + checkOutOptions);

        WebElement deliveryOptionElement = null;


        for (WebElement deliveryOption : checkOutOptions) {
            String deliveryOptionText = deliveryOption.getText();
            System.out.println("deliveryOptionText = " + deliveryOptionText);
            if (deliveryOptionText.equals(delivery)) {
                deliveryOptionElement = deliveryOption;
                break;
            }
            if (deliveryOptionText == null) {
                throw new IllegalArgumentException("invalid delivery type");
            }
        }

        return new BagPage(driver);
    }

    public CheckOutPage checkOut() {
        waitForPresence(By.xpath("//p[@class='bag-total-button-holder']"));
        WebElement checkOutButton = driver.findElement(By.xpath("//a[@class='bag-total-button bag-total-button--checkout bag-total-button--checkout--total']"));
        checkOutButton.click();
        return new CheckOutPage(driver);
    }
}

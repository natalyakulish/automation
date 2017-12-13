package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class PremierDeliveryPage extends Page{

    public PremierDeliveryPage(WebDriver driver){
        super(driver);
    }

    public PremierDeliveryPage SignUpToPremierDelivery() {
        driver.findElement(By.xpath("//*[@data-auto-id='buyPremierButton']/a")).click();
        return new PremierDeliveryPage(driver);
    }

    public PremierDeliveryPage addPremierDeliveryToBag(){
        waitForVisibility(By.xpath("//button[@class='dr-subscription-upgrade_buy']"));
        driver.findElement(By.xpath("//button[@class='dr-subscription-upgrade_buy']")).click();

        return new PremierDeliveryPage(driver);
    }

    public boolean getAddedTick() {
        driver.findElement(By.xpath("//*[@class='dr-subscription-upgrade_buy is-added tick']"));
        return true;
    }

    public String displayErrorMassage() {
        waitForVisibility(By.xpath("//div[@class='dr-message_copy']/p"));
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='dr-message_copy']/p"));
        return errorMessage.getText();
    }
}

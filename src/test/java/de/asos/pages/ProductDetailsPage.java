package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.xpath;

public class ProductDetailsPage extends Page {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage selectFirstAvailableSize() {
        By sizeLocator = By.xpath("//section[@id='core-product']//div[@class=\"size-section\"]/div[@class=\"colour-size-select\"]/select");

        Select select = new Select(driver.findElement(sizeLocator));
        select.selectByIndex(4);
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage addToBag() {
        WebElement element = driver.findElement(xpath("//section[@id='core-product']//a[@class='add-button']"));
        element.click();
        return new ProductDetailsPage(driver);
    }

    public boolean hasMinibagOverlayContainer() {
        waitForVisibility(xpath("//div[@class='minibag-overflow-container']"));
        return true;
    }


}

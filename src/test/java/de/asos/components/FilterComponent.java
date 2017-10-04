package de.asos.components;

import de.asos.exceptions.FilterPanelNotExistsException;
import de.asos.utils.PositionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterComponent extends Component{

    @FindBy(xpath = "//div[@data-id='currentprice']")
    private WebElement currentPrice;

    @FindBy(xpath = "//div[@class='panel']")
    private List<WebElement> filterPanels;

    public FilterComponent(WebDriver driver) {
        super(driver);
    }

    public FilterComponent filterByPrice(int expectedMinPrice, int expectedMaxPrice){
        timeoutSeconds(2);
        System.out.println("--------------");
        System.out.println("Processing min price:");
        processPriceSlider(expectedMinPrice, By.id("userMin"), By.xpath("//div[@class='noUi-handle noUi-handle-lower']"));

        System.out.println("--------------");
        System.out.println("Processing max price:");
        processPriceSlider(expectedMaxPrice, By.id("userMax"), By.xpath("//div[@class='noUi-handle noUi-handle-upper']"));

        return new FilterComponent(driver);

    }

    private void processPriceSlider(int expectedPrice, By priceLocator, By handlerLocator) {
        WebElement priceElement = currentPrice.findElement(priceLocator);
        WebElement handleElement = currentPrice.findElement(handlerLocator);

        int selectedPrice = getSelectedPrice(priceElement);

        if (selectedPrice != expectedPrice) {
            boolean selectedPriceLessThenExpected = false;
            if (selectedPrice < expectedPrice) {
                selectedPriceLessThenExpected = true;
            }
            System.out.println("selectedPriceLessThenExpected = " + selectedPriceLessThenExpected);
            startHoldingHandle(handleElement);
            processSlider(expectedPrice, selectedPrice, selectedPriceLessThenExpected, priceElement, handleElement);
            releaseHandle(handleElement);
        }

        timeoutSeconds(2);
    }

    private void processSlider(int expectedPrice, int selectedPrice, boolean selectedPriceLessThenExpected, WebElement priceElement, WebElement handleElement) {
        if (selectedPrice == expectedPrice){
            return;
        }

        if (selectedPrice > expectedPrice && selectedPriceLessThenExpected){
            return;
        }

        if (selectedPrice < expectedPrice && !selectedPriceLessThenExpected){
            return;
        }

        dragElement(expectedPrice, handleElement, selectedPrice);
        selectedPrice = getSelectedPrice(priceElement);
        System.out.println("selectedPrice after dragging = " + selectedPrice);

        processSlider(expectedPrice, selectedPrice, selectedPriceLessThenExpected, priceElement, handleElement);

    }

    private void releaseHandle(WebElement handleLower) {
        Actions actions = new Actions(driver);
        actions.release(handleLower)
                .build()
                .perform();
    }

    private void startHoldingHandle(WebElement handleLower) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(handleLower)
                .build()
                .perform();
    }

    private void dragElement(int expectedPrice, WebElement handleLower, int selectedPrice) {
        System.out.println("expectedPrice = " + expectedPrice);
        System.out.println("selectedPrice = " + selectedPrice);
        timeoutMilliseconds(500);
        if (selectedPrice < expectedPrice){
            System.out.println("selectedPrice < expectedPrice");
            dragElementHorizontally(handleLower, 2);
        } else {

            System.out.println("selectedPrice > expectedPrice (else is working)");
            dragElementHorizontally(handleLower, -2);
        }
    }

    private void dragElementHorizontally(WebElement handleLower, int xOffset) {
        System.out.println("handleLower.getLocation().getX() = " + handleLower.getLocation().getX());
        Actions actions = new Actions(driver);
        actions.clickAndHold(handleLower)
                .moveByOffset(xOffset, 0)
                .build()
                .perform();
        System.out.println("After dragging:");
        System.out.println("handleLower.getLocation().getX() = " + handleLower.getLocation().getX());
    }

    private int getSelectedPrice(WebElement priceElement) {
        String priceText = priceElement.getText();
        return NumberUtils.toInt(priceText.replace("€", ""));
    }

    public int getSelectedMinPrice() {

        WebElement minPriceElement = currentPrice.findElement(By.id("userMin"));

        return getSelectedPrice(minPriceElement);
    }
    public int getSelectedMaxPrice() {

        WebElement maxPriceElement = currentPrice.findElement(By.id("userMax"));

        return getSelectedPrice(maxPriceElement);
    }

    public FilterComponent filterCategory(String panelName, String panelValue) {
        FilterPanelComponent panel = getPanel(panelName);
        panel.checkValue(panelValue);


        return new FilterComponent(driver);
    }

    private FilterPanelComponent getPanel(String panelName) {
        for (WebElement element : filterPanels){
           try {

               element.findElement(By.xpath(".//div//a/h3/span[text()='" + panelName + "']"));
               return new FilterPanelComponent(element);
           }catch (NoSuchElementException e) {
               //TODO log reason
           }
        }
        throw new FilterPanelNotExistsException(panelName);
    }

    private class FilterPanelComponent {

        private final WebElement webElement;

        public FilterPanelComponent(WebElement webElement) {
            this.webElement = webElement;
        }

        public void checkValue(String panelValue) {
            WebElement elementToClick = webElement.findElement(By.xpath(".//div/ul/li[@data-name='" + panelValue + "'][@data-enabled='true']/a/span[@class='facet-checkbox']"));
            PositionUtils.scrollToElement(driver, elementToClick, 0, 1000);
            elementToClick.click();
        }
    }
}

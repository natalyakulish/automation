package de.asos.components;

import de.asos.exceptions.FilterPanelNotExistsException;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FilterComponent extends Component {

    @FindBy(xpath = "//div[@data-id='currentprice']")
    private WebElement currentPrice;

    @FindBy(xpath = "//div[@class='panel']")
    private List<WebElement> filterPanels;


    @FindBy(xpath = "//div[@class='noUi-handle noUi-handle-lower']")
    private WebElement leftSlider;

    @FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
    private WebElement rightSlider;

    @FindBy(xpath = "//div[@class='noUi-base']")
    private  WebElement slider;

    public FilterComponent(WebDriver driver) {
        super(driver);
    }

    public int getSelectedMinPrice() {
        WebElement minPriceElement = currentPrice.findElement(By.id("userMin"));

        return getSelectedPrice(minPriceElement);
    }

    public int getSelectedMaxPrice() {
        WebElement maxPriceElement = currentPrice.findElement(By.id("userMax"));

        return getSelectedPrice(maxPriceElement);
    }

    private int getSelectedPrice(WebElement priceElement) {
        String priceText = priceElement.getText();
        return NumberUtils.toInt(priceText.replace("€", ""));
    }

    public FilterComponent filterCategory(String panelName, String panelValue) {
        FilterPanelComponent panel = getPanel(panelName);
        panel.checkValue(panelValue);

        return new FilterComponent(driver);
    }

    private FilterPanelComponent getPanel(String panelName) {
        for (WebElement element : filterPanels) {
            try {
                element.findElement(By.xpath(".//div//a/h3/span[text()='" + panelName + "']"));
                return new FilterPanelComponent(element);
            } catch (NoSuchElementException e) {
                //TODO log
            }
        }
        throw new FilterPanelNotExistsException(panelName);
    }

    public FilterComponent filterCategoryByAnyValue(String categoryName) {
        FilterPanelComponent panel = getPanel(categoryName);
        panel.checkFirstEnabledCheckbox();

        return new FilterComponent(driver);
    }

    public FilterComponent checkFirstEnabledCheckboxInAllCategories() {
        WebElement productsOverlay = driver.findElement(By.id("productsOverlay"));
        for (WebElement filterPanel : filterPanels) {
            try {
                FilterPanelComponent filterPanelComponent = new FilterPanelComponent(filterPanel);

                filterPanelComponent.checkFirstEnabledCheckbox();

                waitFor(ExpectedConditions.invisibilityOf(productsOverlay));
            } catch (NoSuchElementException e) {
                //TODO log
            }
        }

        return new FilterComponent(driver);
    }

    public int findCheckedCheckboxes() {
        return driver.findElements(By.xpath("id('productlist-results')/aside/.//div/ul/li[@data-checked='true']/a/span[@class='facet-checkbox']")).size();
    }

    public int findPanelsWithCheckboxes() {
        int result = 0;

        for (WebElement element : filterPanels) {
            try {
                element.findElement(By.xpath(".//div/ul/li//a/span[@class='facet-checkbox']"));
                result++;
            } catch (NoSuchElementException e) {
                //TODO log
            }
        }
        return result;
    }


    public FilterComponent checkProductPrice(int left, int right) throws Exception {
        waitForVisibility(By.xpath("//div[@class='noUi-handle noUi-handle-lower']"));
       int widthSliderBar = slider.getSize().width;
        System.out.println("widthSliderBar = " + widthSliderBar);
        Actions sliderAction = new Actions(driver);
        sliderAction.dragAndDropBy(rightSlider,-50,0).build().perform();

        return new FilterComponent(driver);
    }

    private class FilterPanelComponent {

        private final WebElement webElement;

        public FilterPanelComponent(WebElement webElement) {
            this.webElement = webElement;
        }

        public void checkValue(String panelValue) {
            WebElement checkboxToClick = webElement.findElement(By.xpath(".//div/ul/li[@data-name='" + panelValue + "'][@data-enabled='true']/a/span[@class='facet-checkbox']"));
            checkboxToClick.click();
        }

        public void checkFirstEnabledCheckbox() {
            WebElement checkboxToClick = webElement.findElement(By.xpath(".//div/ul/li[@data-enabled='true']/a/span[@class='facet-checkbox']"));
            checkboxToClick.click();
        }
    }
}

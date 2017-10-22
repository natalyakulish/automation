package de.asos.components;

import de.asos.exceptions.FilterPanelNotExistsException;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FilterComponent extends Component {

    @FindBy(xpath = "//div[@data-id='currentprice']")
    private WebElement currentPrice;

    @FindBy(xpath = "//div[@class='panel']")
    private List<WebElement> filterPanels;

    public FilterComponent(WebDriver driver) {
        super(driver);
    }

    public FilterComponent filterByPrice(int expectedMinPrice, int expectedMaxPrice) { //TODO fix
        timeoutSeconds(2);
        System.out.println("--------------");
        System.out.println("Processing min price:");
//        processPriceSlider(expectedMinPrice, By.id("userMin"), By.xpath("//div[@class='noUi-handle noUi-handle-lower']"));

        System.out.println("--------------");
        System.out.println("Processing max price:");
//        processPriceSlider(expectedMaxPrice, By.id("userMax"), By.xpath("//div[@class='noUi-handle noUi-handle-upper']"));

        return new FilterComponent(driver);

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

package de.asos.pages;

import de.asos.components.FilterComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends Page{

    @FindBy(xpath = "//nav[@class='breadcrumb']//li/a")
    private List<WebElement> breadcrumbItems;

    @FindBy(xpath = "//nav[@class='breadcrumb']//li/span[@class='breadcrumb-current']")
    private WebElement currentBreadcrumb;

    private FilterComponent filterComponent;

    public CategoryPage(WebDriver driver) {
        super(driver);
        filterComponent = new FilterComponent(driver);
    }

    public boolean containsBreadcrumbsItems(String sexCategory, String category) {

        List<String> breadcrumbItemNames = getBreadcrumbItemNames();

        if (breadcrumbItemNames.contains(sexCategory) && breadcrumbItemNames.contains(category)){
            return true;
        }

        return false;
    }

    private List<String> getBreadcrumbItemNames() {
        List<String> result = new ArrayList<String>();

        for (WebElement element: breadcrumbItems ){

            result.add(element.getText());
        }
        result.add(currentBreadcrumb.getText());
        return result;
    }

    public FilterComponent getFilter() {
        return filterComponent;
    }

}

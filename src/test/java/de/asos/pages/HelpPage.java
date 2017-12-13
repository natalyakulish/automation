package de.asos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HelpPage extends Page {
    public HelpPage(WebDriver driver) {
        super(driver);
    }


    public HelpCategoryPage openHelpCategory(String category, String subCategory) {

        System.out.println("driver.getCurrentUrl() = " + driver.getCurrentUrl());
        WebElement helpIframe = driver.findElement(By.xpath("//div[@id='helpIframe']/iframe"));
        WebDriver iFrameDriver = driver.switchTo().frame(helpIframe);

        waitForPresence(By.xpath("//div[@class='div_CategoryBoxFaqs_box_inn']"));
        List<WebElement> helpCategories = iFrameDriver.findElements(By.xpath("//div[@class='div_CategoryBoxFaqs_box_inn']"));
        System.out.println("helpCategories = " + helpCategories);

        WebElement helpCategoryValue = null;

        for (WebElement helpCategory : helpCategories) {
            String helpCategoryText = helpCategory.getText();
            System.out.println("helpCategoryText = " + helpCategoryText);
            if (helpCategoryText.equals(category)) {
                helpCategoryValue = helpCategory;
                break;
            }
        }
        if (helpCategoryValue == null) {
            throw new IllegalArgumentException(category + " is not valid");
        }

        Actions action = new Actions(driver);
        action.moveToElement(helpCategoryValue)
                .pause(500)
                .perform();

        List<WebElement> helpSubCategories = driver.findElements(By.xpath("//div[@class='div_CategoryBoxFaqs_box']//div[@class='div_links_ind']/div/a"));
        WebElement subCategoryElement = null;

        for (WebElement helpSubCategory : helpSubCategories) {
            String helpSubCategoryText = helpSubCategory.getText();
            System.out.println("helpSubCategoryText = " + helpSubCategoryText);
            if (helpSubCategoryText.equals(subCategory)) {
                subCategoryElement = helpSubCategory;
                subCategoryElement.click();
                break;
            }
        }
        if (subCategoryElement == null) {
            throw new IllegalArgumentException(subCategory + "is not valid");
        }

        return new HelpCategoryPage(driver);
    }

    public boolean containsBreadCrumbsItems(String category, String subCategory) {

        return false;
    }
}

package de.asos.utils;

import org.openqa.selenium.*;

public class PositionUtils {

    public static void scrollToElement(WebDriver driver, WebElement element, int xOffset, int yOffset) {
        Point elementLocation = element.getLocation();

        System.out.println("elementLocation = " + elementLocation);

        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        long startOfVisibleLocationX = (Long) jsx.executeScript("return window.pageXOffset;");
        long startOfVisibleLocationY = (Long) jsx.executeScript("return window.pageYOffset;");

        System.out.println("startOfVisibleLocationX = " + startOfVisibleLocationX);
        System.out.println("startOfVisibleLocationY = " + startOfVisibleLocationY);

        Dimension browserWindowSize = driver.manage().window().getSize();

        System.out.println("browserWindowSize = " + browserWindowSize);

        long endOfVisibleLocationX = browserWindowSize.getWidth() + startOfVisibleLocationX;
        long endOfVisibleLocationY = browserWindowSize.getHeight() + startOfVisibleLocationY;

        System.out.println("endOfVisibleLocationX = " + endOfVisibleLocationX);
        System.out.println("endOfVisibleLocationY = " + endOfVisibleLocationY);

        long requiredLocationX = elementLocation.getX() + xOffset;
        long requiredLocationY = elementLocation.getY() + yOffset;

        System.out.println("requiredLocationX = " + requiredLocationX);
        System.out.println("requiredLocationY = " + requiredLocationY);

        long scrollXTo = getScrollToValue(requiredLocationX, startOfVisibleLocationX, endOfVisibleLocationX);
        long scrollYTo = getScrollToValue(requiredLocationY, startOfVisibleLocationY, endOfVisibleLocationY);

        System.out.println("scrollXTo = " + scrollXTo);
        System.out.println("scrollYTo = " + scrollYTo);

        if (scrollXTo != 0 || scrollYTo != 0) {
            scrollXTo = scrollXTo / 3;
            scrollYTo = scrollYTo / 3;

            jsx.executeScript("window.scrollBy(" + scrollXTo + ", " + scrollYTo + ")", "");
            jsx.executeScript("window.scrollBy(" + scrollXTo + ", " + scrollYTo + ")", "");
            jsx.executeScript("window.scrollBy(" + scrollXTo + ", " + scrollYTo + ")", "");
        }
    }

    private static long getScrollToValue(long requiredLocation, long startOfVisibleLocation, long endOfVisibleLocation) {
        long scrollToValue;
        if (requiredLocation >= startOfVisibleLocation) {
            if (requiredLocation < endOfVisibleLocation) {
                scrollToValue = 0;
            } else {
                scrollToValue = requiredLocation - endOfVisibleLocation;
            }
        } else {
            scrollToValue = requiredLocation - startOfVisibleLocation;
        }

        return scrollToValue;
    }

}

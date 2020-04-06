package pageobjects;

import appium.AppiumDriverFactoryAndroid;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageObjectUtils {

    public static void waitLoadFinish(AppiumDriver drv) {
        WebDriverWait wait = new WebDriverWait(drv, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading")));
    }

    public static void scrollToTop() throws InterruptedException {
        AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollToBottom(AppiumDriver driver) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript(" window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    public static void scrollToElement(WebElement element) throws InterruptedException {
        AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public static WebElement scrollToElementGet(WebDriver driver, WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        return element;
    }

    public static List<WebElement> getFilteredListByKey(WebElement element, List<String> keys) {
        List<WebElement> result = new ArrayList<>();
        List<WebElement> itemsList;
        String t = element.getAttribute("innerHTML").contains("<tr") ? "tr" : "li";
        itemsList = element.findElements(By.tagName(t));
        result.addAll(itemsList.stream().filter(item -> keys.stream().allMatch(key -> item.getText().toLowerCase().contains(key.toLowerCase()))).collect(Collectors.toList()));
        return result;
    }

    public static List<WebElement> getFilteredListByKey(WebElement element, String key) {
        return getFilteredListByKey(element, Collections.singletonList(key));
    }

    public static WebElement getSpecificItemFromListElement(WebElement element, String fullItemName) throws InterruptedException {
        scrollToTop();
        String t = element.getAttribute("innerHTML").contains("<tr") ? "tr" : "li";
        List<WebElement> itemsList = element.findElements(By.tagName(t));
        for (WebElement item : itemsList) {
            if (item.getText().toLowerCase().contains(fullItemName.toLowerCase())) {
                return item;
            }
        }
        Assert.assertTrue("The item " + fullItemName.toLowerCase() + " was not found in the list", false);
        return null;
    }

    private static int getIndexOfSpecificItemFromListElement(WebElement element, String fullItemName) {
        String t = element.getAttribute("innerHTML").contains("<tr") ? "tr" : "li";
        List<WebElement> itemsList = element.findElements(By.tagName(t));
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getText().toLowerCase().contains(fullItemName.toLowerCase())) {
                return i;
            }
        }
        Assert.assertTrue("The item " + fullItemName.toLowerCase() + " was not found in the list", false);
        return 0;
    }

    public static WebElement getNextSpecificItemFromListElement(WebElement element, String fullItemName) {
        String t = element.getAttribute("innerHTML").contains("<tr") ? "tr" : "li";
        List<WebElement> itemsList = element.findElements(By.tagName(t));
        return itemsList.get(PageObjectUtils.getIndexOfSpecificItemFromListElement(element, fullItemName) + 1);
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static By getElementByLocator(LocatorType locatorType, String id) {
        switch (locatorType) {
            case CLASSNAME:
                return By.className(id);
            case CSS:
                return By.cssSelector(id);
            case ID:
                return By.id(id);
            case LINK:
                return By.linkText(id);
            case NAME:
                return By.name(id);
            case TAGNAME:
                return By.tagName(id);
            case XPATH:
                return By.xpath(id);
        }
        return null;
    }

    public static ByAccessibilityId getElementByAccessibilityID(LocatorType locatorType, String id) {
        switch (locatorType) {
            case ACCESSIBILITYID:
                return new ByAccessibilityId(id);
        }
        return null;
    }


    static MobileElement getElementByAccessibilityIDFromPairs(List<Pair<LocatorType, String>> locatorPairs) throws InterruptedException {
        AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        MobileElement mobileElement = null;
        waitLoadFinish(driver);
        for (Pair<LocatorType, String> loc : locatorPairs) {
            mobileElement = (MobileElement) driver.findElement(getElementByAccessibilityID(loc.getKey(), loc.getValue()));
        }
        return mobileElement;
    }


    static WebElement getElementFromPairs(List<Pair<LocatorType, String>> locatorPairs) throws InterruptedException {
        AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = null;
        waitLoadFinish(driver);
        for (Pair<LocatorType, String> loc : locatorPairs) {
            if (element == null)
                element = driver.findElement(getElementByLocator(loc.getKey(), loc.getValue()));
            else element = element.findElement(getElementByLocator(loc.getKey(), loc.getValue()));
        }
        return element;
    }

    static List<WebElement> getElementsFromPairs(List<Pair<LocatorType, String>> locatorPairs) throws InterruptedException {
        AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        List<WebElement> elementList = null;
        waitLoadFinish(driver);
        for (Pair<LocatorType, String> loc : locatorPairs) {
            elementList = driver.findElements(getElementByLocator(loc.getKey(), loc.getValue()));
        }
        return elementList;
    }


    public static By getElementLocatorFromPairs(List<Pair<LocatorType, String>> locatorPairs) {
        By requiredElement = null;
        for (Pair<LocatorType, String> loc : locatorPairs) {
            if (!loc.getValue().isEmpty())
                requiredElement = getElementByLocator(loc.getKey(), loc.getValue());
            break;
        }
        return requiredElement;
    }



    public static WebElement locateElement(AppiumDriver webDriver, LocatorType type, String ref) {
        switch (type) {
            case ID:
                return webDriver.findElement(By.id(ref));
            case CLASSNAME:
                return webDriver.findElement(By.className(ref));
            case XPATH:
                return webDriver.findElement(By.xpath(ref));
            case CSS:
                return webDriver.findElement(By.cssSelector(ref));
            case LINK:
                return webDriver.findElement(By.linkText(ref));
            case NAME:
                return webDriver.findElement(By.name(ref));
            case TAGNAME:
                return webDriver.findElement(By.tagName(ref));
        }
        return null;
    }

    public static WebElement getElementFromPairs(LocatorType locType, String id) throws InterruptedException {
        List<Pair<LocatorType, String>> locatorPairs = new ArrayList<>();
        locatorPairs.add(Pair.of(locType, id));
        return getElementFromPairs(locatorPairs);
    }

    public static List<WebElement> getElementsFromPairs(LocatorType locType, String id) throws InterruptedException {
        List<Pair<LocatorType, String>> locatorPairs = new ArrayList<>();
        locatorPairs.add(Pair.of(locType, id));
        return getElementsFromPairs(locatorPairs);
    }

    public static By getElementLocatorFromPairs(LocatorType locType, String id) throws InterruptedException {
        List<Pair<LocatorType, String>> locatorPairs = new ArrayList<>();
        locatorPairs.add(Pair.of(locType, id));
        return getElementLocatorFromPairs(locatorPairs);
    }

    public static MobileElement getElementByAccessibilityIDFromPairs(LocatorType locType, String id) throws InterruptedException {
        List<Pair<LocatorType, String>> locatorPairs = new ArrayList<>();
        locatorPairs.add(Pair.of(locType, id));
        return getElementByAccessibilityIDFromPairs(locatorPairs);
    }




    public enum LocatorType {
        CLASSNAME, CSS, ID, LINK, NAME, TAGNAME, XPATH, ACCESSIBILITYID;
    }


}

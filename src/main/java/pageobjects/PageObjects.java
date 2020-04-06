package pageobjects;

import appium.AppiumDriverFactoryAndroid;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public interface PageObjects {
    AppiumDriver driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
    List<Pair<PageObjectUtils.LocatorType, String>> locatorPairs = new ArrayList<>();

    WebDriverWait wait = new WebDriverWait(driver, 15);

    default String getPageTitle() {
        return driver.getTitle();
    }

    default void clickElement() {
        try {
            WebElement element = getElement();
            waitUntilElementIsVisible(10);
            waitUntilElementIsClickable(driver,10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }

    default boolean isElementPresent() throws InterruptedException {
       // waitLoadFinish(driver);
        WebElement element = getElement();
        return  element.isDisplayed();
    }

    default WebElement getElement() throws InterruptedException {
        initializeMap();
        return PageObjectUtils.getElementFromPairs(locatorPairs);
    }

    default MobileElement getMobileElement() throws InterruptedException{
        initializeMap();
        return PageObjectUtils.getElementByAccessibilityIDFromPairs(locatorPairs);
    }

    default void sendKeys(CharSequence... text) throws InterruptedException {
        WebElement element = getElement();
        if(element.getAttribute("class").contains("hidden") && System.getProperty("websiteTestingBrowser").equals("safari")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].className='block'", element);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("block")));
        }
        element.sendKeys(text);
    }

    default void clear() throws InterruptedException {
        WebElement element = getElement();
        element.clear();
    }

    default void waitUntilElementIsClickable(WebDriver driver, long timeOut) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }
    }

    default void waitUntilElementIsEnabled(WebDriver driver, long timeOut) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }
    }


    default void waitUntilElementIsVisible(WebDriver driver, long timeOut) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }

    default void waitUntilElementIsVisible(long timeOut)  {

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
       // WebDriverWait wait = new WebDriverWait(driver, timeOut);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }

    default void waitUntilElementIsInvisible(WebDriver driver, long timeOut) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
    }

    default String getText() throws InterruptedException {
        waitUntilNumberOfElementsIsGreaterThanZero();
        waitUntilElementIsVisible(driver, 10);
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        return getElement().getText().toLowerCase();
    }

    default void waitUntilNumberOfElementsIsGreaterThanZero() throws InterruptedException {
        initializeMap();
        List<By> byList = new ArrayList<>();
        for (Pair<PageObjectUtils.LocatorType, String> loc : locatorPairs) {
            byList.add(PageObjectUtils.getElementByLocator(loc.getKey(), loc.getValue()));
        }
        for (By by : byList) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        }
    }

    void initializeMap();


}

package stepdefs;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportingUtil.Report;


import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CommonStepObjects {
    protected static AppiumDriver driver;
    protected static String CurrentScenario;
    protected static String FeatureFileName = "";
    protected static ExtentReports extent;
    protected static String packageName;
    protected static ExtentTest test;
    protected static boolean blnExecutionStatus;
    protected static boolean StartFlag = true;
    private static final int TIMEOUT_SECONDS = 40;


    public static void closeDriver() {
        driver.quit();
    }

    public void catchException(Exception e, ExtentTest test) {
        test.log(LogStatus.ERROR, "Reason for failure is: " + e.getMessage(), test.addScreenCapture(Report.CaptureScreen(driver)));
        System.out.println("Setup Variable value in exception: " + e.getMessage());
        driver.launchApp();
        Assert.fail();
    }


    public static boolean isElementDisplayed(By locator) {
        try {
            if ((driver.findElement(locator).isDisplayed())) ;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void clickElementWithIndex(final By Locator, final int index) {
        List<WebElement> ElementList = driver.findElements(Locator);
        ElementList.get(index).click();
    }

    public static String getElementTextWithIndex(final By Locator, final int index) {
        List<WebElement> ElementList = driver.findElements(Locator);
        return ElementList.get(index).getText();
    }

    public static void waitUntilElementIsInVisible(final long timeOutInSeconds, final By by) {
        waitUntil(timeOutInSeconds, ExpectedConditions.invisibilityOfElementLocated(by));
    }


    public static <T> T waitUntil(long pollEveryMs, Function<WebDriver, ?> function) {
        return waitUntil(pollEveryMs, TIMEOUT_SECONDS, function);
    }

    protected static <T> T waitUntil(Long pollEveryMs, long timeOutAfterSeconds, Function<WebDriver, ?> function) {
        final WebDriverWait wait = new WebDriverWait(driver, timeOutAfterSeconds);
        wait.pollingEvery(Duration.ofMillis(pollEveryMs));
        return (T) wait.until(function);
    }


    public static void scrollUpAndroidAndClick(By locator, String movieName) throws Exception {
        int maxCounter = 25;
        int Cntr = 0;
        while (!isElementFoundById(locator, movieName) && Cntr < maxCounter) {
            TouchAction touchAction = new TouchAction(driver);
            Dimension size = driver.manage().window().getSize();
            int starty = (int) (size.height * 0.50);
            int endy = (int) (size.height * 0.20);
            int startx = size.width / 2;
            touchAction.longPress(PointOption.point(startx, starty)).moveTo(PointOption.point(startx, endy)).release().perform();
            Thread.sleep(1000);
            touchAction = null;
            Cntr++;
        }
        if (Cntr >= maxCounter) {
            throw new Exception("Element not found" + locator);
        } else {
            driver.findElement(locator).click();
        }

    }

    public static boolean isElementFoundById(WebElement element, String text) {
        try {
            return element.getText().equalsIgnoreCase(text);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementFoundById(By locator, String text) {
        try {
            return  driver.findElement(locator).getText().equalsIgnoreCase(text);
        } catch (Exception e) {
            return false;
        }
    }


    public static String getTextOfToastMessage() {
        WebElement toastView = driver.findElement(By.xpath("//android.widget.Toast"));
        return toastView.getText();
    }

    public static void waitForToastMessageDisappear() {
        waitUntilElementIsInVisible(10, By.xpath("//android.widget.Toast"));

    }

}
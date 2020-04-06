package pageobjects.pages;

import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;

import java.util.List;

public enum CatalogueDetailPage implements PageObjects {

    //Try with Text //*[contains(text(),'Movie Catalogue')]
    CATALOGUE_HEADER_TEXT(PageObjectUtils.LocatorType.XPATH,"//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"),
    CATALOGUE_TEXT_CONTAINER(PageObjectUtils.LocatorType.XPATH,"//android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView"),
    CATALOGUE_RATING(PageObjectUtils.LocatorType.XPATH,"//android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RatingBar"),
    FAVORITE_UNFAVORITE_BUTTON(PageObjectUtils.LocatorType.ID,"favorite_button"),
    NAVIGATE_UP(PageObjectUtils.LocatorType.ACCESSIBILITYID,"Navigate up"),
    SKIP(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView");




    public static boolean isHomeScreenPresent() throws InterruptedException {
        Thread.sleep(5000);
        WebElement navigation_header_container = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu_mobility_header"))));
        Assert.assertTrue("Failed to show menu bar and quick parking button", navigation_header_container.isDisplayed());
        return navigation_header_container.isDisplayed();
    }

    private PageObjectUtils.LocatorType locType;

    private String id;

    CatalogueDetailPage(PageObjectUtils.LocatorType loc, String id) {
        this.locType = loc;
        this.id = id;
    }

    @Override
    public void initializeMap() {
    }

    @Override
    public String getPageTitle() {
        return null;
    }

    @Override
    public WebElement getElement() throws InterruptedException {
        return PageObjectUtils.getElementFromPairs(locType, id);
    }

    public List<WebElement> getElements() throws InterruptedException {
        return PageObjectUtils.getElementsFromPairs(locType, id);
    }

    public By getLocator() throws InterruptedException{
        return PageObjectUtils.getElementLocatorFromPairs(locType,id);
    }
    public MobileElement getMobileElement() throws InterruptedException {
        return PageObjectUtils.getElementByAccessibilityIDFromPairs(locType, id);
    }

}

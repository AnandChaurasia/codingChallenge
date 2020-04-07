package pageobjects.pages;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;

public enum CatalogueDetailPage implements PageObjects {

    CATALOGUE_HEADER_TEXT(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"),
    CATALOGUE_TEXT_CONTAINER(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView"),
    CATALOGUE_RATING(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RatingBar"),
    FAVORITE_UNFAVORITE_BUTTON(PageObjectUtils.LocatorType.ID, "favorite_button"),
    NAVIGATE_UP(PageObjectUtils.LocatorType.ACCESSIBILITYID, "Navigate up");


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


    public By getLocator() throws InterruptedException {
        return PageObjectUtils.getElementLocatorFromPairs(locType, id);
    }

    public MobileElement getMobileElement() throws InterruptedException {
        return PageObjectUtils.getElementByAccessibilityIDFromPairs(locType, id);
    }

}

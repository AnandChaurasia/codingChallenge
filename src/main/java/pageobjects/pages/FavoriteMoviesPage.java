package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;


public enum FavoriteMoviesPage implements PageObjects {

    FAVORITE_MOVIES_HEADER_TEXT(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"),
    TV_SHOW_TAB(PageObjectUtils.LocatorType.XPATH, "//androidx.appcompat.app.ActionBar.Tab[@content-desc='TV SHOW']"),
    POSTER(PageObjectUtils.LocatorType.ID, "imageMovies");

    private PageObjectUtils.LocatorType locType;

    private String id;

    FavoriteMoviesPage(PageObjectUtils.LocatorType loc, String id) {
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
}
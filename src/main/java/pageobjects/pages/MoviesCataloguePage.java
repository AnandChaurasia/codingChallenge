package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;

public enum MoviesCataloguePage implements PageObjects {

    MOVIES_TAB(PageObjectUtils.LocatorType.XPATH, "//androidx.appcompat.app.ActionBar.Tab[@content-desc='MOVIES']"),
    TV_SHOW_TAB(PageObjectUtils.LocatorType.XPATH, "//androidx.appcompat.app.ActionBar.Tab[@content-desc='TV SHOW']"),
    POSTER_TITLE(PageObjectUtils.LocatorType.ID, "titleMovies"),
    MOVIES_POSTER(PageObjectUtils.LocatorType.XPATH, "//android.widget.ImageView[@content-desc='image poster']"),
    FAVORITE_ICON(PageObjectUtils.LocatorType.ID, "favorite");


    private PageObjectUtils.LocatorType locType;

    private String id;

    MoviesCataloguePage(PageObjectUtils.LocatorType loc, String id) {
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

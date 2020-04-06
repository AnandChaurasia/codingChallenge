package pageobjects.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;

import java.util.List;

public enum  FavoriteMoviesPage implements PageObjects {
    //Try with Text //*[contains(text(),'Movie Catalogue')]
    FAVORITE_MOVIES_HEADER_TEXT(PageObjectUtils.LocatorType.XPATH, "//android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"),
    //id.ihwan.jetpackpro.debug:id/imageMovies
    TV_SHOW_TAB(PageObjectUtils.LocatorType.XPATH,"//androidx.appcompat.app.ActionBar.Tab[@content-desc='TV SHOW']"),
    POSTER(PageObjectUtils.LocatorType.ID,"imageMovies");


    public static boolean isHomeScreenPresent() throws InterruptedException {
        Thread.sleep(5000);
        WebElement navigation_header_container = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu_mobility_header"))));
        Assert.assertTrue("Failed to show menu bar and quick parking button", navigation_header_container.isDisplayed());
        return navigation_header_container.isDisplayed();
    }

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

    public List<WebElement> getElements() throws InterruptedException {
        return PageObjectUtils.getElementsFromPairs(locType, id);
    }

    public By getLocator() throws InterruptedException {
        return PageObjectUtils.getElementLocatorFromPairs(locType, id);
    }
}
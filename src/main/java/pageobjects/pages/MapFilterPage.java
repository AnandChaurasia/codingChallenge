package pageobjects.pages;

import org.openqa.selenium.WebElement;
import pageobjects.PageObjectUtils;
import pageobjects.PageObjects;

public enum MapFilterPage implements PageObjects {

    DONE_BUTTON(PageObjectUtils.LocatorType.ID, "toolbarMenuButton"),

    ANZEIGEN_TAB(PageObjectUtils.LocatorType.XPATH, "//android.widget.TextView[contains(@text, 'Anzeigen')]"),
    SHOW_ALL_BUTTON(PageObjectUtils.LocatorType.ID, "firstOptionButton"),
    ON_STREET_BUTTON(PageObjectUtils.LocatorType.ID, "secondOptionButton"),
    OFF_STREET_BUTTON(PageObjectUtils.LocatorType.ID, "thirdOptionButton"),
    //ONLY_PARKENPLUS_SWITCH(PageObjectUtils.LocatorType.XPATH, "//android.widget.Switch[contains(@text, 'Nur Parken Plus')]"),
    //ONLY_AVAILABLE_SWITCH(PageObjectUtils.LocatorType.XPATH, "//android.widget.Switch[contains(@text, 'Nur verfügbare Parkhäußer')]"),
    //PREIS_INFO_SWITCH(PageObjectUtils.LocatorType.XPATH, "//android.widget.Switch[contains(@text, 'Preisinformationen')]"),
    ONLY_PARKENPLUS_SWITCH(PageObjectUtils.LocatorType.XPATH,"//android.widget.LinearLayout[1]/android.widget.Switch"),
    ONLY_AVAILABLE_SWITCH(PageObjectUtils.LocatorType.XPATH, "//android.widget.LinearLayout[2]/android.widget.Switch"),
    PREIS_INFO_SWITCH(PageObjectUtils.LocatorType.XPATH, "//android.widget.LinearLayout[3]/android.widget.Switch"),

//    RFID Authentifizierung need to be added(will be implemented in V2)

    RESET(PageObjectUtils.LocatorType.ID, "resetFilterView");

    private final PageObjectUtils.LocatorType locType;
    private final String id;

    MapFilterPage(PageObjectUtils.LocatorType loc, String id) {
        this.locType = loc;
        this.id = id;

    }

    @Override
    public void initializeMap() {

    }

    @Override
    public WebElement getElement() throws InterruptedException {
        return PageObjectUtils.getElementFromPairs(locType, id);
    }
}

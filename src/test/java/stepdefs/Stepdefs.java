package stepdefs;

import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import enums.Context;
import generalUtils.ScenarioContext;
import org.junit.Assert;
import pageobjects.pages.CatalogueDetailPage;
import pageobjects.pages.FavoriteMoviesPage;
import pageobjects.pages.MoviesCataloguePage;
import reportingUtil.Report;

public class Stepdefs extends CommonStepObjects {
    ScenarioContext scenarioContext;

    public Stepdefs(ScenarioContext context) {
        scenarioContext = context;
    }

    @Given("^The app is launched$")
    public void the_app_is_launched() {
        try {
            if (!CommonStepObjects.isElementDisplayed(MoviesCataloguePage.FAVORITE_ICON.getLocator())) {
                driver.launchApp();
                Thread.sleep(5000);
            }
            test = extent.startTest(CurrentScenario, CurrentScenario);
            test.log(LogStatus.INFO, "Application is Launched and Catalogue screen appear ", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Given("^User is on 'Movie Catalogue' page$")
    public void userIsOnMovieCataloguePage() {
        try {
            MoviesCataloguePage.MOVIES_TAB.getElement().click();
            test.log(LogStatus.PASS, "Movies Tab is selected ", test.addScreenCapture(Report.CaptureScreen(driver)));
            Assert.assertTrue(CatalogueDetailPage.CATALOGUE_HEADER_TEXT.isElementPresent());
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @When("^User selects a item from catalogue page$")
    public void userSelectsItemFromCataloguePage() {
        try {
            CommonStepObjects.clickElementWithIndex(MoviesCataloguePage.MOVIES_POSTER.getLocator(), 0);
            Assert.assertTrue(CatalogueDetailPage.CATALOGUE_HEADER_TEXT.isElementPresent());
            test.log(LogStatus.PASS, "Movie is selected from catalogue and its detail screen is displayed ", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @And("^User clicks on the 'ADD TO FAVORITE' button$")
    public void userClicksOnTheADDTOFAVORITEButton() {
        try {
            final String movieName = CatalogueDetailPage.CATALOGUE_HEADER_TEXT.getText();
            scenarioContext.setContext(Context.NAME_HEADER_FROM_CATALOGUE, movieName);
            test.log(LogStatus.PASS, "Movie selected to add in favorite is: " + movieName);
            CatalogueDetailPage.FAVORITE_UNFAVORITE_BUTTON.clickElement();
            String value = CommonStepObjects.getTextOfToastMessage();
            Assert.assertTrue(value.equalsIgnoreCase("Added To Favorite!"));
            test.log(LogStatus.PASS, "Movie is added to favorite, confirmation message is: " + value, test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Then("^Selected movie is successfully added to Favorite list$")
    public void selectedMovieIsSuccessfullyAddedToFavoriteList() {
        try {
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
            MoviesCataloguePage.FAVORITE_ICON.clickElement();
            test.log(LogStatus.PASS, "Favorite Movie' page is displayed ", test.addScreenCapture(Report.CaptureScreen(driver)));
            Assert.assertTrue(FavoriteMoviesPage.FAVORITE_MOVIES_HEADER_TEXT.getElement().isDisplayed());
            FavoriteMoviesPage.POSTER.clickElement();
            final String movieName = CatalogueDetailPage.CATALOGUE_HEADER_TEXT.getText();
            Assert.assertTrue(scenarioContext.getContext(Context.NAME_HEADER_FROM_CATALOGUE).equals(movieName));
            test.log(LogStatus.PASS, "Movie is successfully added to Favorite list: " + scenarioContext.getContext(Context.NAME_HEADER_FROM_CATALOGUE), test.addScreenCapture(Report.CaptureScreen(driver)));
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Given("^User is on 'Favorite Movie' page$")
    public void userIsOnFavoriteMoviePage() {
        try {
            MoviesCataloguePage.FAVORITE_ICON.clickElement();
            test.log(LogStatus.PASS, "Favorite Movie' page is displayed ", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @When("^User selects item from favorite list$")
    public void userSelectsItemFromFavoriteList() {
        try {
            FavoriteMoviesPage.POSTER.clickElement();
            test.log(LogStatus.PASS, "Favorite item is selected:", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }

    }

    @And("^User clicks on the 'UNFAVORITE' button$")
    public void userClicksOnTheUNFAVORITEButton() {
        try {
            CatalogueDetailPage.FAVORITE_UNFAVORITE_BUTTON.clickElement();
            Assert.assertTrue(CommonStepObjects.getTextOfToastMessage().equalsIgnoreCase("Deleted from Favorite!"));
            test.log(LogStatus.PASS, "Item is deleted from favorite on catalogue detail screen", test.addScreenCapture(Report.CaptureScreen(driver)));
            waitForToastMessageDisappear();
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Then("^Selected movie is successfully deleted from Favorite Movie list$")
    public void selectedMovieIsSuccessfullyDeletedFromFavoriteMovieList() {
        try {
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
            if (!CommonStepObjects.isElementDisplayed(FavoriteMoviesPage.POSTER.getLocator())) {
                test.log(LogStatus.PASS, "Movie is deleted from favorite list:", test.addScreenCapture(Report.CaptureScreen(driver)));
            } else {
                throw new Exception("'UNFAVORITE' button is not working");
            }

        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Given("^User is on 'TV Show Catalogue' page$")
    public void userIsOnTVShowCataloguePage() {
        try {
            MoviesCataloguePage.TV_SHOW_TAB.getElement().click();
            test.log(LogStatus.PASS, "TV Show Tab is selected ", test.addScreenCapture(Report.CaptureScreen(driver)));
            Assert.assertTrue(CatalogueDetailPage.CATALOGUE_HEADER_TEXT.isElementPresent());
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Then("^Selected TV show is successfully added to Favorite list$")
    public void selectedTVShowIsSuccessfullyAddedToFavoriteList() {
        try {
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
            MoviesCataloguePage.FAVORITE_ICON.clickElement();
            Assert.assertTrue(FavoriteMoviesPage.FAVORITE_MOVIES_HEADER_TEXT.getElement().isDisplayed());
            FavoriteMoviesPage.TV_SHOW_TAB.clickElement();
            test.log(LogStatus.PASS, "Favorite TV Show' screen is displayed ", test.addScreenCapture(Report.CaptureScreen(driver)));
            FavoriteMoviesPage.POSTER.clickElement();
            final String TVShowName = CatalogueDetailPage.CATALOGUE_HEADER_TEXT.getText();
            Assert.assertTrue(scenarioContext.getContext(Context.NAME_HEADER_FROM_CATALOGUE).equals(TVShowName));
            test.log(LogStatus.PASS, "Movie is successfully added to Favorite list: " + scenarioContext.getContext(Context.NAME_HEADER_FROM_CATALOGUE), test.addScreenCapture(Report.CaptureScreen(driver)));
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Given("^User is on 'Favorite TV Show' page$")
    public void userIsOnFavoriteTVShowPage() {
        try {
            MoviesCataloguePage.FAVORITE_ICON.clickElement();
            MoviesCataloguePage.TV_SHOW_TAB.getElement().click();
            test.log(LogStatus.PASS, "Favorite TV Show' page is displayed ", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Then("^Selected TV Show is successfully deleted from Favorite TV Show list$")
    public void selectedTVShowIsSuccessfullyDeletedFromFavoriteTVShowList() {
        try {
            CatalogueDetailPage.NAVIGATE_UP.getMobileElement().click();
            if (!CommonStepObjects.isElementDisplayed(FavoriteMoviesPage.POSTER.getLocator())) {
                test.log(LogStatus.PASS, "TV Show is deleted from favorite list:", test.addScreenCapture(Report.CaptureScreen(driver)));
            } else {
                throw new Exception("'UNFAVORITE' button is not working");
            }

        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @When("^User selects poster \"([^\"]*)\"$")
    public void userSelectsPoster(String arg0)  {
        try {
            CommonStepObjects.scrollUpAndroidAndClick(MoviesCataloguePage.POSTER_TITLE.getElement(), arg0);
            test.log(LogStatus.PASS, "Movie from catalog is selected ", test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @Then("^User Verifies title of selected item$")
    public void userVerifiesTitleOfSelectedItem() {
        try {
            String titleOfMovieInHeader = CatalogueDetailPage.CATALOGUE_HEADER_TEXT.getText();
            String titleOfMovieInTextContainer = CommonStepObjects.getElementTextWithIndex(CatalogueDetailPage.CATALOGUE_TEXT_CONTAINER.getLocator(), 0);
            Assert.assertTrue(titleOfMovieInHeader.equalsIgnoreCase(titleOfMovieInTextContainer));
            test.log(LogStatus.PASS, "Movie title is: " + titleOfMovieInTextContainer, test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @And("^User checks rating of selected item$")
    public void userChecksRatingOfSelectedItem() {
        try {
            String TextContainer = CommonStepObjects.getElementTextWithIndex(CatalogueDetailPage.CATALOGUE_RATING.getLocator(), 0);
            Assert.assertFalse(TextContainer.isEmpty());
            test.log(LogStatus.PASS, "Movie rating date is displayed  as: " + Float.parseFloat(TextContainer)/10);
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @And("^User checks release date of selected item$")
    public void userChecksReleaseDateOfSelectedItem() {
        try {
            String TextContainer = CommonStepObjects.getElementTextWithIndex(CatalogueDetailPage.CATALOGUE_TEXT_CONTAINER.getLocator(), 1);
            Assert.assertFalse(TextContainer.isEmpty());
            test.log(LogStatus.PASS, "Movie release date is displayed  as: " + TextContainer);
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @And("^User check movie summary is displayed$")
    public void userChecksSummaryOfMovie() {
        try {
            String TextContainer = CommonStepObjects.getElementTextWithIndex(CatalogueDetailPage.CATALOGUE_TEXT_CONTAINER.getLocator(), 2);
            Assert.assertFalse(TextContainer.isEmpty());
            test.log(LogStatus.PASS, "Movie summary is displayed  as: " + TextContainer, test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }

    @And("^User check TV Show summary is displayed$")
    public void userCheckTVShowSummaryIsDisplayed() {
        try {
            String TextContainer = CommonStepObjects.getElementTextWithIndex(CatalogueDetailPage.CATALOGUE_TEXT_CONTAINER.getLocator(), 1);
            Assert.assertFalse(TextContainer.isEmpty());
            test.log(LogStatus.PASS, "TV Show summary is displayed  as: " + TextContainer, test.addScreenCapture(Report.CaptureScreen(driver)));
        } catch (Exception e) {
            catchException(e, test);
        }
    }
}

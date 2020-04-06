package features;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import stepdefs.CommonStepObjects;

@RunWith(Cucumber.class)

@CucumberOptions(monochrome = true,

        features = "src/test/java/features",
        glue = {"stepdefs"},
        tags = {"@Ready"})


public class FeaturesRunner {
     @AfterClass
    public static void TearDown() {
        CommonStepObjects.closeDriver();
    }
}
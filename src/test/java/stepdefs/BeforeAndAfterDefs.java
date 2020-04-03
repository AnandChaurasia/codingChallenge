package stepdefs;

import appium.AppiumDriverFactoryAndroid;
import appium.AppiumServer;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//import reportingUtil.Report;

import java.util.Map;

public class BeforeAndAfterDefs extends CommonStepObjects {
    AppiumServer appiumServer = new AppiumServer();
    int port = 4723;

    @Before
    public void beforeTest(Scenario scenario) {
        Map<String, Object> driverDetails;

        try {
             if (!CommonStepObjects.FeatureFileName.equals(scenario.getId().split(";")[0])) {
                 CommonStepObjects.StartFlag = true;
             }
            if (CommonStepObjects.StartFlag) {
                CommonStepObjects.StartFlag = false;
                CommonStepObjects.FeatureFileName = scenario.getId().split(";")[0];
                if(!appiumServer.checkIfServerIsRunning(port)) {
                    appiumServer.startServer();
                    //appiumServer.stopServer();
                } else {
                    System.out.println("Appium Server already running on Port - " + port);
                }

           driver = AppiumDriverFactoryAndroid.getInstance().getDriver();
                driverDetails = CommonStepObjects.driver.getSessionDetails();
              //  extent = Report.Instance(scenario.getId().split(";")[0], driverDetails);
                CommonStepObjects.CurrentScenario = scenario.getId().split(";")[1];
            }
            if (!scenario.getId().split(";")[1].equalsIgnoreCase("stop")) {
                CommonStepObjects.CurrentScenario = scenario.getId().split(";")[1];
            } else {
                CommonStepObjects.extent.endTest(CommonStepObjects.test);
                CommonStepObjects.extent.flush();
                CommonStepObjects.extent.close();
            }

            CommonStepObjects.blnExecutionStatus = true;
        } catch (
                Exception e) {
            CommonStepObjects.test.log(LogStatus.ERROR, "Test failure due to: " + e.getMessage());
        }

    }


    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            CommonStepObjects.test.log(LogStatus.FAIL, "Failed scenario is" + scenario.getName());
        }
        CommonStepObjects.extent.endTest(CommonStepObjects.test);
        CommonStepObjects.extent.flush();



    }
}


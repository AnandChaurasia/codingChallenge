package reportingUtil;

import com.relevantcodes.extentreports.ExtentReports;
import generalUtils.JsonReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Report {
    public static String imgPath;
    public static String strPath;
    public static String testCase;

    public static ExtentReports Instance(String TestCase, Map<String, Object> driverDetails) {
        Extent dir = new Extent();
        strPath = dir.getPath() + TestCase + "/";
        try {
            File oFilePathTillResults = new File(strPath);
            if (!oFilePathTillResults.exists()) {
                oFilePathTillResults.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        testCase = TestCase;
        ExtentReports extent;
        String Path = strPath + TestCase + ".html";
        imgPath = Path.substring(0, Path.length() - 5);
        System.out.println(Path);
        String buildVersion = JsonReader.getBuildVersion();

        extent = new ExtentReports(Path, false);
        extent
                .addSystemInfo("Project Name", "PPA-CodeChallenge")
                .addSystemInfo("Build Version", buildVersion)
                .addSystemInfo("Environment", "Test")
                .addSystemInfo("platformVersion", driverDetails.get("platformVersion").toString())
                .addSystemInfo("platformName", driverDetails.get("platformName").toString());
        if (driverDetails.get("platformName").toString().equalsIgnoreCase("ios")) {
            extent.addSystemInfo("deviceName", driverDetails.get("deviceName").toString());
        } else {
            extent.addSystemInfo("deviceModel", driverDetails.get("deviceModel").toString());
        }
        return extent;
    }

    public static String CaptureScreen(WebDriver driver) {
        SimpleDateFormat sd = new SimpleDateFormat("ddMMyyHHmmssSSS");
        Date now = new Date();
        String strName = sd.format(now);
        String strScrPath = strPath + strName;
        org.openqa.selenium.TakesScreenshot oScn = (org.openqa.selenium.TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(strScrPath + ".jpg");
        try {
            FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return strName + ".jpg";
    }

}



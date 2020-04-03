package appium;

import generalUtils.JsonReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class AppiumDriverFactoryAndroid {
    private static boolean isAndroid = true;
    protected static JsonReader reader = JsonReader.getInstance(isAndroid);
    protected static final String LOCAL_URL="http://127.0.0.1:4723/wd/hub";
    public AppiumDriverFactoryAndroid(){
}
    public AppiumDriver getDriver() {
        return driver;
    }
    private static AppiumDriverFactoryAndroid ourInstance = new AppiumDriverFactoryAndroid();
    public static AppiumDriverFactoryAndroid getInstance() {
        return ourInstance;
    }
    private AppiumDriver driver = createDriver();
    private AppiumDriver createDriver() {
        String URL;
        DesiredCapabilities androidCaps = new DesiredCapabilities();
            if (isAndroid) {
                androidCaps.setCapability("deviceName", reader.getDeviceName());
                androidCaps.setCapability("platformName", reader.getPlatformName());
                androidCaps.setCapability("udid", reader.getUdid());
                androidCaps.setCapability("app", System.getProperty("user.dir") + "\\src\\main\\resources\\"+ reader.getApp());
                androidCaps.setCapability("appPackage", reader.getAppPackage());
                androidCaps.setCapability("appActivity", reader.getAppActivity());
                androidCaps.setCapability("bundleID", reader.getBundleID());
                androidCaps.setCapability("noReset",true);
                androidCaps.setCapability("fullReset", false);
                try {
                    driver = new AppiumDriver(new URL(LOCAL_URL), androidCaps);
                    System.out.println("Driver is created");
                    Thread.sleep(5000);
                } catch (MalformedURLException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        return driver;
    }
}

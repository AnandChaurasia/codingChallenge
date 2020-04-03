package generalUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
public class JsonReader {

    private String udid;
    private String platformName;
    private String os_version;
    private String automationName;
    private String platformVersion;
    private String appPackage;
    private String appPath;
    private String noReset;
    private String appiumServerUrl;
    private String browserStackHost;
    private String bsUserName;
    private String bsAccessKey;
    private String app;
    private String deviceName;
    private String appWaitActivity;
    private String bundleID;
    private String appActivity;
    private List devices;

    private static String buildVersion;

    private JSONParser parser;
    private static JsonReader instance;

    public static JsonReader getInstance(boolean isAndroid) {
        instance = new JsonReader("config-device.json", isAndroid);
        return instance;
    }

    private JsonReader(String jsonPath, boolean isAndroid) {
        parser = new JSONParser();
        try (FileReader fr = new FileReader(jsonPath)) {
            JSONObject config = (JSONObject) parser.parse(fr);
            parseConfig(config, isAndroid);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseConfig(JSONObject config, boolean isAndroid) {
        JSONArray os;
            os = (JSONArray) config.get("local");
            if (isAndroid) {
                //Android
                JSONObject android = (JSONObject) os.get(0);
                System.out.println(android);
                this.deviceName = (String) android.get("deviceName");
                this.platformName = (String) android.get("platformName");
                this.app = (String) android.get("app");
                this.udid = (String) android.get("udid");
                this.appWaitActivity = (String) android.get("appWaitActivity");
                this.appPackage = (String) android.get("appPackage");
                this.appActivity = (String) android.get("appActivity");
                this.bundleID = (String) android.get("bundleID");
                buildVersion = (String) android.get("buildVersion");

            } else {
                //iOS
                JSONObject ios = (JSONObject) os.get(1);
                System.out.println(ios);
                this.platformName = (String) ios.get("platformName");
                this.deviceName = (String) ios.get("deviceName");
                this.app = (String) ios.get("app");
                this.automationName = (String) ios.get("automationName");
                this.devices = (List) ios.get("devices");
                buildVersion = (String) ios.get("buildVersion");
            }

        }



    public String getUdid() {
        return udid;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getAutomationName() {
        return automationName;
    }


    public String getAppPackage() {
        return appPackage;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getNoReset() {
        return noReset;
    }

    public String getAppiumServerUrl() {
        return appiumServerUrl;
    }

    public String getBundleID() {
        return bundleID;
    }

    public String getAppWaitActivity() {
        return appWaitActivity;
    }

    public String getBrowserStackHost() {
        return browserStackHost;
    }

    public String getBrowserStackUserName() {
        return bsUserName;
    }

    public String getBrowserStackAccessKey() {
        return bsAccessKey;
    }

    public String getApp() {
        return app;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public List getDevices() {
        return devices;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public String getPlatformVersion() { return platformVersion; }

    public String getOsVersion() { return os_version; }

    public static String getBuildVersion() {
        return buildVersion;
    }

}


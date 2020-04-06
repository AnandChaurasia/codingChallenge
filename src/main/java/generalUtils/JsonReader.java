package generalUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class JsonReader {

    private String udid;
    private String platformName;
    private String appPackage;
    private String app;
    private String deviceName;
    private String appWaitActivity;
    private String bundleID;
    private String appActivity;
    private String networkSpeed;


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
        }
    }


    public String getUdid() {
        return udid;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getBundleID() {
        return bundleID;
    }

    public String getApp() {
        return app;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public static String getBuildVersion() {
        return buildVersion;
    }

    public String getNetworkSpeed() {
        return networkSpeed;
    }


}


package reportingUtil;

import java.io.File;
public class Extent {

    static String strAbsolutepath = new File("").getAbsolutePath();

    public String getPath() {
        String sPathTillResults;
        sPathTillResults = strAbsolutepath + "/Results";

        try {
            File oFilePathTillResults = new File(sPathTillResults);
            if (!oFilePathTillResults.exists()) {
                oFilePathTillResults.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sPathTillResults + "/";
    }
}


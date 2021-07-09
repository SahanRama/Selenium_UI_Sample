package common;


import util.BaseUtil;

import java.text.SimpleDateFormat;

public class Base {
    private static BaseUtil baseUtil = new BaseUtil();

    private Base() {
    }

    public static void initializeConfigs(String testName) {
        baseUtil.initializeLogger(testName);
        baseUtil.setCoreConfig();
    }

    public static void openBrowser() {
        baseUtil.loadBrowser();
    }

    public static void navigateToHomePage() {
        baseUtil.navigateToHomePage();
    }


    public static String generateEmail() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new java.util.Date());
        System.out.println(timeStamp);
        return "sahan" + timeStamp + "@pastbook.com";

    }

    public static void tearDown() {
        baseUtil.tearDown();
    }


}

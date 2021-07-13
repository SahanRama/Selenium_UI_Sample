package base;

import common.Base;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert softAssert;
    protected String email;
    protected String testName;

    @BeforeClass()
    public void openHomePage() {
        Base.initializeConfigs(testName);
        Base.openBrowser();
    }

    @BeforeMethod
    public void navigateToHomePage() {
        Base.navigateToHomePage();
    }

    @BeforeMethod()
    public void initializeSoftAssert() {
        this.softAssert = new SoftAssert();
    }

    @BeforeGroups("EmailGenerate")
    public void emailGenerate() {
        this.email = Base.generateEmail();
    }


    @AfterMethod
    public void navigateToHome() {
        //Base.navigateToHomePage();
    }

    @AfterSuite
    public void closeBrowser() {
        Base.tearDown();
    }
}

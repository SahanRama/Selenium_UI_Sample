package util;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static io.github.bonigarcia.wdm.config.DriverManagerType.*;
import static util.PageActions.waitForSeconds;

public class BaseUtil {
    protected static WebDriver driver;
    protected static Logger log;
    protected static String currentDirectory;
    private static String mainWindow;
    private static String applicationURL;
    private static String environment;
    protected ConfigurationManager coreConfig;
    private String browser;

    public static void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        mainWindow = currentWindow;
    }

    public void initializeLogger(String testName) {
        //Initialize the log4j logger
        log = LogManager.getLogger(testName);
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        log.info("Initiating the test execution.");
        log.info("Started Executing :" + testName);
    }

    public void setCoreConfig() {
        //Set the core configurations
        currentDirectory = System.getProperty("user.dir");
        String coreConfigPath = currentDirectory + "/config/coreconfig.cfg";
        log.debug("Core configurations path: " + coreConfigPath);
        coreConfig = new ConfigurationManager();
        coreConfig.setConfiguration(coreConfigPath);
        log.info("Initialized the configurations.");
    }


    public void loadBrowser() {
        if (coreConfig.getProperty("browser") != null) {
            this.browser = coreConfig.getProperty("browser").toUpperCase();
            switch (browser) {
                case "FIREFOX":
                    WebDriverManager.getInstance(FIREFOX).setup();
                    driver = new FirefoxDriver(getFirefoxOptions());
                    log.debug("Browser: FIREFOX");
                    break;
                case "IE":
                    WebDriverManager.getInstance(IEXPLORER).setup();
                    driver = new InternetExplorerDriver();
                    log.debug("Browser: INTERNET_EXPLORER");
                    break;
                case "EDGE":
                    WebDriverManager.getInstance(EDGE).setup();
                    driver = new EdgeDriver();
                    log.debug("Browser: EDGE");
                    break;
                case "CHROME":
                default:
                    WebDriverManager.getInstance(CHROME).setup();
                    driver = new ChromeDriver(getChromeOptions());
                    log.debug("Browser: CHROME");
                    break;
            }
        }
    }

    private ChromeOptions getChromeOptions() {
        log.debug("Started setting the chrome options.");

        ChromeOptions options = new ChromeOptions();

        boolean isHeadless = Boolean.parseBoolean(coreConfig.getProperty("isHeadless"));
        boolean disableInfoBars = Boolean.parseBoolean(coreConfig.getProperty("disableInfoBars"));

        if (disableInfoBars) {
            options.addArguments("disable-infobars");
            log.info("Disabled the info bars.");
        }
        options.setHeadless(isHeadless);
        log.debug("Headless: " + String.valueOf(isHeadless).toUpperCase());
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        log.debug("Started setting the firefox options.");

        FirefoxOptions options = new FirefoxOptions();
        boolean isHeadless = Boolean.parseBoolean(coreConfig.getProperty("isHeadless"));
        options.setHeadless(isHeadless);
        log.debug("Headless: " + String.valueOf(isHeadless).toUpperCase());
        return options;
    }

    public void navigateToHomePage() {

        if (coreConfig.getProperty("environment") != null) {
            environment = coreConfig.getProperty("environment").toUpperCase();
            switch (environment) {
                case "PRODUCTION":
                    applicationURL = coreConfig.getProperty("PRODUCTION_url");
                    log.info("Application URL: " + applicationURL);
                    driver.get(applicationURL);
                    log.debug("Environment: PRODUCTION");
                    break;
                case "QA":
                    applicationURL = coreConfig.getProperty("QA_url");
                    log.info("Application URL: " + applicationURL);
                    driver.get(applicationURL);
                    log.debug("Environment: QA");
                    break;
                case "STAGING":
                    applicationURL = coreConfig.getProperty("STAGING_url");
                    log.info("Application URL: " + applicationURL);
                    driver.get(applicationURL);
                    log.debug("Environment: STAGING");
                    break;
                default:
                    applicationURL = coreConfig.getProperty("QA_url");
                    driver.get(applicationURL);
                    log.warn("There is a problem with the environment configurations. Please check the configurations again! Environment set to QA by default configurations.");
                    break;
            }
        }
    }


    public void switchToMainWindow() {
        driver.switchTo().window(mainWindow);
        waitForSeconds(3);
        log.info("Switch to Main Window");
    }

    public void closeWindow() {
        driver.close();
        driver.switchTo().window(mainWindow);
        log.info("Closed the Window.");
    }

    public void tearDown() {
        driver.quit();
        log.info("Closed the webdriver.");
    }

}

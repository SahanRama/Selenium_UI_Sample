package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageActions extends BaseUtil {
    public static boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public static void waitForSeconds(int waitTimeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(waitTimeInSeconds);
        } catch (InterruptedException e) {
            log.error("Interrupted Exception:", e);
            Thread.currentThread().interrupt();
        }
    }

    public void hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void clickOnAlertAccept() {
        driver.switchTo().alert().accept();
    }

    public void clickOnAlertDismiss() {
        driver.switchTo().alert().dismiss();
    }

    public void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToMainFrame() {
        driver.switchTo().parentFrame();
    }

    public void waitTillGivenTime(long timeInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    public void uploadFileUsingAbsolutePath(By findElementBy, String absolutePathOfFile) {
        driver.findElement(findElementBy).sendKeys(absolutePathOfFile);
    }

    public void scrollToElement(WebElement element) {
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor) driver).executeScript(script, element);
        waitForSeconds(3);
    }

    private void executeJavaScriptCode(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public void scrollPageVertically() {
        executeJavaScriptCode("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollPageHorizontally() {
        executeJavaScriptCode("window.scrollTo(document.body.scrollWidth, 0)");
    }

    public void waitForElement(By locator, int timeout) {
        new WebDriverWait(driver, timeout).until
                (ExpectedConditions.visibilityOfElementLocated(locator));
    }

}

package pages;

import org.openqa.selenium.By;
import util.PageActions;

public class SecureAreaPage extends PageActions {
    private final By statusAlert = By.id("flash");


    public String getAlertText() {
        String statusAlertText = driver.findElement(statusAlert).getText();
        log.info("Status Alert text is: '" + statusAlertText + "'.");
        return statusAlertText;
    }

}

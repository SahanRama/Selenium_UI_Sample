package pages;


import org.openqa.selenium.By;
import util.PageActions;

public class HomePagePage extends PageActions {

    private final By formAuthenticationLink = By.linkText("Form Authentication");


    public void clickFormAuthentication() {
        waitForElement(formAuthenticationLink, 5);
        driver.findElement(formAuthenticationLink).click();

    }
}

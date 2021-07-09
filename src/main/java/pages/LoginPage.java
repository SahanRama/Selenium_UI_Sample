package pages;

import org.openqa.selenium.By;
import util.PageActions;

public class LoginPage extends PageActions {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login button");


    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        log.debug("Entered the username: '" + username + "'.");
    }


    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        log.debug("Entered the password: '" + password + "'.");
    }


    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        log.debug("Clicked on the 'Login' button.");
        log.info("Navigating to the SecureAreaPage after successful login attempt.");
    }

}

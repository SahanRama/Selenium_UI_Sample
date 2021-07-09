package login;

import base.BaseTest;
import functions.HomePage;
import functions.Login;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(description = "Verify the successful login scenario.")
    public void verifySuccessfulLogin() {

        HomePage.navigateToSignInPage();
        Login.loginToTheSystem("tomsmith", "SuperSecretPassword!");
        softAssert.assertTrue(Login.verifyUserLoggedInSuccessfully().contains("You logged into a secure area!"), "Alert text is incorrect.");
    }

}

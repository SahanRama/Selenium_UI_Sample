package functions;

import pages.LoginPage;
import pages.SecureAreaPage;

public class Login {
    private static LoginPage loginPage = new LoginPage();
    private static SecureAreaPage secureAreaPage = new SecureAreaPage();


    public static void loginToTheSystem(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    public static String verifyUserLoggedInSuccessfully() {
        return secureAreaPage.getAlertText();
    }

}

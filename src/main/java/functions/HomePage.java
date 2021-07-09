package functions;

import pages.HomePagePage;

public class HomePage {
    private static HomePagePage homePagePage = new HomePagePage();

    public static void navigateToSignInPage() {
        homePagePage.clickFormAuthentication();
    }
}

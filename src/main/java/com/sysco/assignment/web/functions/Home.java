package com.sysco.assignment.web.functions;

import com.sysco.assignment.web.common.Constants;
import com.sysco.assignment.web.pages.HomePage;
import com.sysco.assignment.web.pages.LoginPage;
import com.sysco.assignment.web.utils.DriverSetUpUtil;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Home {
    public static HomePage homePage = new HomePage();


    public static void loadHomePage() {

        if (Constants.RUN_LOCALLY) {
            DriverSetUpUtil.setToRunLocally();
            DesiredCapabilities capabilities = null;
            homePage.loadHomePage(capabilities, Constants.APP_URL);
        } else {
            homePage.loadHomePage(DriverSetUpUtil.setToRunRemotely(Constants.APP_OS), Constants.APP_URL);
        }
    }

    public static boolean verifyLoginLink() {
        return homePage.isLoginLinkExist();
    }

    public static void clickLoginLink(){
        homePage.clickLoginLink();
    }
    public static void quiteDriver() {
        homePage.quitDriver();
    }
}

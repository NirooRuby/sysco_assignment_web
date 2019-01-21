package com.sysco.assignment.web.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

public class HomePage {
    protected static SyscoLabUI syscoLabUIOgm;
    private By lnkLogin = By.cssSelector("[class='authorization-link -notlogin']");
    public static void loadHomePage(Capabilities capabilities, String url) {
        syscoLabUIOgm = new SyscoLabWUI(capabilities);
        syscoLabUIOgm.driver.manage().window().maximize();
        syscoLabUIOgm.navigateTo(url);
    }

    public boolean isLoginLinkExist() {
        return syscoLabUIOgm.isDisplayed(lnkLogin);
    }

    public LoginPage clickLoginLink() {
        syscoLabUIOgm.click(lnkLogin);
        return new LoginPage(syscoLabUIOgm);
    }
    public void quitDriver() {
        if (syscoLabUIOgm != null) {
            syscoLabUIOgm.quit();
        }
    }
}

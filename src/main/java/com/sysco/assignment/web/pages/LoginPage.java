package com.sysco.assignment.web.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

/**
 * Created by Rifad on 5/21/18.
 */
public class LoginPage {
    protected static SyscoLabUI syscoLabUIOgm;
    private By txtGoogleSearch = By.id("lst-ib");


    public static void loadLoginPage(Capabilities capabilities, String url) {
        syscoLabUIOgm = new SyscoLabWUI(capabilities);
        syscoLabUIOgm.navigateTo(url);
        syscoLabUIOgm.driver.manage().window().maximize();
    }

    public void quitDriver() {
        if (syscoLabUIOgm != null) {
            syscoLabUIOgm.quit();
        }
    }


    public void enterText(String searchString) {
        syscoLabUIOgm.sendKeys(txtGoogleSearch, searchString);
    }

}

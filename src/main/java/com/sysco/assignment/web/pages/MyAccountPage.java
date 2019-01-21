package com.sysco.assignment.web.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import org.openqa.selenium.By;

public class MyAccountPage {
    protected static SyscoLabUI syscoLabUIOgm;
    private static By lnkMyAccount = By.cssSelector("[class='authorization-link -login']");
    private static By lblUserName = By.cssSelector("[class='greet welcome'] span");

    public MyAccountPage(){

    }
    public MyAccountPage(SyscoLabUI syscoLabUIOgm){
        this.syscoLabUIOgm = syscoLabUIOgm;
    }
    public static String verifyUserName() {
        return syscoLabUIOgm.getText(lblUserName);
    }

    public static boolean verifyMyAccountLink() {
        return syscoLabUIOgm.isDisplayed(lnkMyAccount);
    }
}

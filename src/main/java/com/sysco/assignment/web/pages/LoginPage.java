package com.sysco.assignment.web.pages;

import com.sysco.assignment.web.functions.MyAccount;
import com.syscolab.qe.core.ui.SyscoLabUI;
import com.syscolab.qe.core.ui.web.SyscoLabWUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

public class LoginPage {
    protected static SyscoLabUI syscoLabUIOgm;
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("pass");
    private By btnLogin = By.id("send2");
    private static By LblInCorrectSignIn = By.xpath("//div[@class='message-error error message']/div");


    public LoginPage(){
        /*Constructor*/
    }
    public LoginPage(SyscoLabUI syscoLabUIOgm){
        this.syscoLabUIOgm = syscoLabUIOgm;
    }

    public boolean isLoginElementsExist() {
            return syscoLabUIOgm.isDisplayed(txtEmail) && syscoLabUIOgm.isDisplayed(txtPassword)
                    &&syscoLabUIOgm.isDisplayed(btnLogin);
    }

    public void enterEmail(String email) {
        syscoLabUIOgm.sendKeys(txtEmail,email);
    }

    public void enterPassword(String password) {
        syscoLabUIOgm.sendKeys(txtPassword,password);
    }

    public MyAccountPage clickLoginButton() {
        syscoLabUIOgm.click(btnLogin);
        return new MyAccountPage(syscoLabUIOgm);
    }

    public static String verifyLoginFunctionError() {
        syscoLabUIOgm.waitTillElementLoaded(LblInCorrectSignIn);
        return syscoLabUIOgm.getText(LblInCorrectSignIn);
    }
}

package com.sysco.assignment.web.functions;


import com.sysco.assignment.web.pages.LoginPage;


public class Login  {

    public static LoginPage loginPage = new LoginPage();

    public static boolean isLoginElementsExist() {
        return loginPage.isLoginElementsExist();
    }

    public static void enterEmailAndPassword(String email, String password){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
    public static void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    public static boolean verifyEmailBoxErrorMessage() {
        return false;
    }

    public static String verifyLoginFunctionError() {
        return LoginPage.verifyLoginFunctionError();
    }
}

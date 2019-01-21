package com.sysco.assignment.web.functions;

import com.sysco.assignment.web.pages.MyAccountPage;

public class MyAccount {
    public static MyAccountPage myAccountPage = new MyAccountPage();

    public static boolean verifyMyAccountLink() {
        return myAccountPage.verifyMyAccountLink();
    }

    public static String verifyUserName() {
        return myAccountPage.verifyUserName();
    }
}

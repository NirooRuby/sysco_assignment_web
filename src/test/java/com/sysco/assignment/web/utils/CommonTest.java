package com.sysco.assignment.web.utils;

import com.sysco.assignment.web.functions.Home;

public class CommonTest {
    public static void login(String username, String password) throws Exception{
        //this has only login flow without any verifications
        Home.loadHomePage();
        Home.clickLoginLink();
        com.sysco.assignment.web.functions.Login.enterEmailAndPassword(username, password);
        com.sysco.assignment.web.functions.Login.clickLoginButton();
    }
}

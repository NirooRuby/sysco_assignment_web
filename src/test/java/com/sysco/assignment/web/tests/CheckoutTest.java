package com.sysco.assignment.web.tests;

import org.testng.annotations.Test;

public class CheckoutTest {
    @Test(dependsOnMethods = "com.sysco.assignment.web.tests.LoginTest.testLogin")
    public void addToCart(){
        System.out.println("test");
    }
}

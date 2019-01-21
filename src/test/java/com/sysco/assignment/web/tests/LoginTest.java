package com.sysco.assignment.web.tests;


import com.sysco.assignment.web.data.LoginData;
import com.sysco.assignment.web.functions.Home;
import com.sysco.assignment.web.functions.Login;
import com.sysco.assignment.web.functions.MyAccount;
import com.sysco.assignment.web.utils.ExcelUtil;
import com.sysco.assignment.web.utils.TestBase;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTest extends TestBase {

    @BeforeClass
    public void init(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Login - ValidLogin");
    }

    @Test(description = "Test Login functionality", alwaysRun = true)
    public void testLogin() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        // Sample way to retrive data from excel
        LoginData loginData = ExcelUtil.getLoginData("$login1");

        //UI Automation  sample
        Home.loadHomePage();
        Assert.assertTrue(Home.verifyLoginLink(), "Login link is not appear in home page");
        Home.clickLoginLink();
        Assert.assertTrue(Login.isLoginElementsExist(),"Mandatory elements email or password or login button are not appear in login page");
        Login.enterEmailAndPassword(loginData.email, loginData.password);
        Login.clickLoginButton();
        Assert.assertTrue(MyAccount.verifyMyAccountLink(), "My account link is not appear after login");
        softAssert.assertEquals(MyAccount.verifyUserName(), LoginData.userName);
        Home.quiteDriver();

    }
    @Test(description = "adding items to card", alwaysRun = true, dependsOnMethods = "testLogin")
    public void itemsAddToCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();
    }
}
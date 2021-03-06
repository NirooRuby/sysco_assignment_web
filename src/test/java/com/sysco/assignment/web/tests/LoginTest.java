package com.sysco.assignment.web.tests;


import com.sysco.assignment.web.common.Attributes;
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
        iTestContext.setAttribute("feature", "Online Shopping - ValidAndInvalidLogin");
    }

    @Test(description = "Test Login functionality and verify username appear in dashboard.", alwaysRun = true)
    public void testSuccessfulLogin() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        //Getting login data from excel
        LoginData loginData = ExcelUtil.getLoginData("$login1");
        Home.loadHomePage();
        Assert.assertTrue(Home.verifyLoginLink(), Attributes.LOGIN_LINK_MISSING_ERROR);
        Home.clickLoginLink();
        Assert.assertTrue(Login.isLoginElementsExist(),Attributes.LOGIN_ELEMENTS_MISSING_ERROR);
        Login.enterEmailAndPassword(loginData.email, loginData.password);
        Login.clickLoginButton();
        Assert.assertTrue(MyAccount.verifyMyAccountLink(), Attributes.MY_ACCOUNT_LINK_MISSING_ERROR);
        softAssert.assertEquals(MyAccount.verifyUserName(), LoginData.userName);
    }

    @Test(description = "Verify invalid login with in correct username and password", alwaysRun = true)
    public void testLoginInValidEmailAndPassword() throws Exception {
        LoginData loginData = ExcelUtil.getLoginData("$Invalid_login1");
        Home.loadHomePage();
        Assert.assertTrue(Home.verifyLoginLink(), Attributes.LOGIN_LINK_MISSING_ERROR);
        Home.clickLoginLink();
        Assert.assertTrue(Login.isLoginElementsExist(),Attributes.LOGIN_ELEMENTS_MISSING_ERROR);
        Login.enterEmailAndPassword(loginData.email, loginData.password);
        Login.clickLoginButton();
        Assert.assertEquals(Login.verifyLoginFunctionError(), Attributes.SIGNIN_INCORRECT);
        Home.quiteDriver();
    }
}
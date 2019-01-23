package com.sysco.assignment.web.tests;

import com.sysco.assignment.web.common.Attributes;
import com.sysco.assignment.web.data.CartData;
import com.sysco.assignment.web.data.CheckoutData;
import com.sysco.assignment.web.data.LoginData;
import com.sysco.assignment.web.functions.Checkout;
import com.sysco.assignment.web.functions.MyAccount;
import com.sysco.assignment.web.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest {
    @Test(dependsOnMethods = "com.sysco.assignment.web.tests.LoginTest.testSuccessfulLogin")
    public void addToCart(){
        SoftAssert softAssert = new SoftAssert();
        CartData cartData = ExcelUtil.getCartData("$Item1");
        LoginData loginData = ExcelUtil.getLoginData("$login1");
        CheckoutData checkoutData = ExcelUtil.getCheckoutData("$data1");
        MyAccount.removeExistingCartItems();
        MyAccount.navigateToCategoryPage(cartData.category);
        Assert.assertTrue(MyAccount.verifySubMenuWindowOpen(), Attributes.SUb_MENU_WINDOW_ERROR);
        MyAccount.navigateToSubCategoryPage(cartData.category, cartData.subCategory);
        MyAccount.navigateToItemsPage(cartData.itemName);
        softAssert.assertTrue(MyAccount.verifyPageTitle(cartData.itemName), Attributes.PRODUCT_TITLE_MISSING_ERROR);
        MyAccount.selectSize(cartData.itemSize);
        Assert.assertTrue(MyAccount.verifyIsSizeSelected(cartData.itemSize), Attributes.PRODUCT_SIZE_NOT_SELECTED);
        cartData.itemPrice=MyAccount.getItemPrice();
        MyAccount.clickCheckout();
        MyAccount.openMinicart();
        Assert.assertTrue(MyAccount.isItemNameAndPriceExistInMiniCart(cartData.itemName, cartData.itemPrice),"");
        MyAccount.ClickProceedToCheckout();
        Assert.assertTrue(Checkout.verifyFirstNameAndLastName(loginData.userName));
        softAssert.assertTrue(Checkout.verifyContinueWithoutMandatoryField(), "Failed to validate error message for each mandatory field");
        Checkout.enterRequiredFieldsAndContinue(checkoutData.streetAddress,checkoutData.postCode, checkoutData.phoneNumber);
        Checkout.clickContinue();
        Checkout.selectPaymentType(checkoutData.paymentType);
        Assert.assertTrue(Checkout.verifyCartPaymentFieldsAvailable(), "");
        softAssert.assertTrue(Checkout.verifyRequiredFieldValidation(), "");
    }
}

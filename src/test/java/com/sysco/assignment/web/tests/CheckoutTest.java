package com.sysco.assignment.web.tests;

import com.sysco.assignment.web.common.Attributes;
import com.sysco.assignment.web.data.CartData;
import com.sysco.assignment.web.data.CheckoutData;
import com.sysco.assignment.web.data.LoginData;
import com.sysco.assignment.web.functions.Checkout;
import com.sysco.assignment.web.functions.Home;
import com.sysco.assignment.web.functions.MyAccount;
import com.sysco.assignment.web.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest {
    @Test(description = "Remove items if existing items available in mini cart", dependsOnMethods = "com.sysco.assignment.web.tests.LoginTest.testSuccessfulLogin")
    public void removeExistingItems() {
        SoftAssert softAssert = new SoftAssert();
        MyAccount.openMinicart();
        MyAccount.removeExistingCartItems();
        softAssert.assertTrue(MyAccount.verifyItemsRemoved(), Attributes.ITEM_NOT_REMOVED_FROM_MINICART);
        MyAccount.closeMiniCart();
    }

    @Test(description = "Adding item/s to cart and verify item name and item price in mini cart", dependsOnMethods = "removeExistingItems")
    public void addToCartAndVerifyItemAdded() {
        SoftAssert softAssert = new SoftAssert();
        CartData cartData = ExcelUtil.getCartData("$Item1");
        MyAccount.navigateToCategoryPage(cartData.category);
        Assert.assertTrue(MyAccount.verifySubMenuWindowOpen(), Attributes.SUb_MENU_WINDOW_ERROR);
        MyAccount.navigateToSubCategoryPage(cartData.category, cartData.subCategory);
        MyAccount.navigateToItemsPage(cartData.itemName);
        softAssert.assertTrue(MyAccount.verifyPageTitle(cartData.itemName), Attributes.PRODUCT_TITLE_MISSING_ERROR);
        MyAccount.selectSize(cartData.itemSize);
        Assert.assertTrue(MyAccount.verifyIsSizeSelected(cartData.itemSize), Attributes.PRODUCT_SIZE_NOT_SELECTED);
        cartData.itemPrice=MyAccount.getItemPrice();
        MyAccount.clickAddToCartButton();
        MyAccount.openMinicart();
        Assert.assertTrue(MyAccount.isItemNameAndPriceExistInMiniCart(cartData.itemName, cartData.itemPrice),Attributes.NAME_OR_PRICE_NOT_MATCHING);
    }

    @Test(description = "Do checkout and validate payment fields.", dependsOnMethods = "addToCartAndVerifyItemAdded")
    public void CheckoutAndValidatePaymentPage() {
        SoftAssert softAssert = new SoftAssert();
        LoginData loginData = ExcelUtil.getLoginData("$login1");
        CheckoutData checkoutData = ExcelUtil.getCheckoutData("$data1");
        MyAccount.ClickProceedToCheckout();
        Assert.assertTrue(Checkout.verifyFirstNameAndLastName(loginData.userName));
        softAssert.assertTrue(Checkout.verifyContinueWithoutMandatoryField(),  Attributes.CHECKOUT_ELEMENTS_MISSING_ERROR);
        Checkout.enterRequiredFieldsAndContinue(checkoutData.streetAddress, checkoutData.postCode, checkoutData.phoneNumber);
        Checkout.clickContinue();
        Checkout.selectPaymentType(checkoutData.paymentType);
        Assert.assertTrue(Checkout.verifyCartPaymentFieldsAvailable(), "");
        Assert.assertTrue(Checkout.verifyCardPaymentRequiredFieldValidation(CheckoutData.Cardnumber, CheckoutData.month, checkoutData.year), "");
        Checkout.quiteDriver();
    }
}

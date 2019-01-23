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
    public static void removeExistingCartItems() {
        myAccountPage.removeExistingCartItems();
    }

    public static void navigateToCategoryPage(String category) {
        myAccountPage.navigateToCategoryPage(category);
    }

    public static void navigateToSubCategoryPage(String category, String subCategory) {
        myAccountPage.navigateToSubCategoryPage(category, subCategory);
    }

    public static boolean verifyPageTitle(String title) {
        return myAccountPage.verifyPageTitle(title);
    }

    public static void navigateToItemsPage(String itemName) {
        myAccountPage.navigateToItemsPage(itemName);
    }

    public static void selectSize(String itemSize) {
        myAccountPage.selectSize(itemSize);
    }

    public static boolean verifyIsSizeSelected(String itemSize) {
        return myAccountPage.isSizeSelected(itemSize);
    }

    public static boolean verifySubMenuWindowOpen() {
        return myAccountPage.isSubMenuWindowOpen();
    }

    public static void ClickProceedToCheckout() {
            myAccountPage.clickProceedToCheckout();
    }

    public static String getItemPrice() {
        return myAccountPage.getItemPrice();
    }

    public static boolean verifyProductPageLoaded() {
        return myAccountPage.isProductPageLoaded();
    }

    public static void openMinicart() {
        myAccountPage.openMiniCart();
    }

    public static boolean isItemNameAndPriceExistInMiniCart(String itemName, String itemPrice) {
        return myAccountPage.isItemNameAndPriceExistInMiniCart(itemName, itemPrice);
    }

    public static void clickCheckout() {
        myAccountPage.clickAddToCartButton();
    }
}

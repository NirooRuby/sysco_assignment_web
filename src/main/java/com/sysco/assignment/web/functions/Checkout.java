package com.sysco.assignment.web.functions;

import com.sysco.assignment.web.pages.CheckoutPage;

public class Checkout {
    public static CheckoutPage checkoutPage = new CheckoutPage();

    public static boolean verifyFirstNameAndLastName(String userName) {
        return checkoutPage.isFirstNameAndLastNameExist(userName);
    }

    public static boolean verifyContinueWithoutMandatoryField() {
        return checkoutPage.isContunueFunctionHighlightMandatoryFieldErrors();
    }

    public static void enterRequiredFieldsAndContinue(String streetAdress1, String postCode, String phoneNumber) {
        checkoutPage.enterStreetAddress1(streetAdress1);
        checkoutPage.enterPostalCode(postCode);
        checkoutPage.enterPhoneNumber(phoneNumber);
    }

    public static void clickContinue() {
        checkoutPage.clickContinue();
    }

    public static void selectPaymentType(String paymentType) {
        checkoutPage.selectCardPayment(paymentType);
    }

    public static boolean verifyCartPaymentFieldsAvailable() {
        return checkoutPage.isCartPaymentFieldsAvailable();
    }

    public static boolean verifyCardPaymentRequiredFieldValidation(String creditCardNumber, String month, String year) {
        return checkoutPage.isEmptyRequiredFieldHasErrorMessage(creditCardNumber,month,year);
    }
    public static void quiteDriver() {
        CheckoutPage.quitDriver();
    }
}

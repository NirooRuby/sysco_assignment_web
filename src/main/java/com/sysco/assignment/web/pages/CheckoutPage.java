package com.sysco.assignment.web.pages;

import com.syscolab.qe.core.ui.SyscoLabUI;
import fitlibraryGeneric.specify.workflow.Check;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    protected static SyscoLabUI syscoLabUIOgm;
    private static By txtFirstName = By.xpath("//li[@id='shipping']//input[@name='firstname']") ;
    private static By txtLastName = By.xpath("//li[@id='shipping']//input[@name='lastname']") ;
    private static By txtPhoneNumber = By.xpath("//li[@id='shipping']//input[@name='telephone']") ;
    private static By txtStreetAddress1 = By.xpath("//li[@id='shipping']//input[@name='street[0]']") ;
    private static By txtCity = By.xpath("//li[@id='shipping']//input[@name='city']") ;
    private static By drpStates = By.xpath("//li[@id='shipping']//select[@name='region_id']") ;
    private static By txtPostalCode = By.xpath("//li[@id='shipping']//input[@name='postcode']") ;
    private static By txtCountry = By.xpath("//li[@id='shipping']//input[@name='country_id']") ;
    private static By lblRequiredFieldError = By.xpath("//div[@class='field-error mage-error']") ;
    private static By drpPostCode = By.xpath("//ul[@id='ui-id-2']") ;
    private static By drpPostCodeList = By.xpath("//ul[@id='ui-id-2']/li") ;
    private static By btnContinue = By.xpath("//button[@title='Continue']") ;
    private static By rdBtnCardPayment = By.xpath("//ul[@class='credit-card-types types-list']/parent::label/preceding-sibling::input") ;
    private static By rdBtnPaypalPayment = By.xpath("//button[@title='Continue']") ;
    private static By rdBtnAfterPayPayment = By.xpath("//button[@title='Continue']") ;
    private static By txtCreditCard = By.id("credit-card-number") ;
    private static By txtExpirationMonth = By.id("expiration-month") ;
    private static By txtExpirationYear = By.id("expiration-year") ;
    private static By txtCvv = By.id("cvv") ;
    private static By btnPlaceOrder = By.xpath("//div[@class=\"opc-submit-step\"]//button[@title=\"Place Order']") ;

    public CheckoutPage(){
        /*
        * Constructor*/
    }
    public CheckoutPage(SyscoLabUI syscoLabUIOgm){
        this.syscoLabUIOgm = syscoLabUIOgm;
    }

    public static boolean isFirstNameAndLastNameExist(String userName) {
        String[] names = userName.split(" ");
        return syscoLabUIOgm.findElement(txtFirstName).getAttribute("value").trim().equals(names[0]) &&
                syscoLabUIOgm.findElement(txtLastName).getAttribute("value").trim().equals(names[1]);
    }

    public static boolean isRequiredFieldHasValidationError(By elementLocator){
        WebElement element = syscoLabUIOgm.findElement(elementLocator).findElement(lblRequiredFieldError);
        return syscoLabUIOgm.isDisplayed(element);
    }

    public static boolean isContunueFunctionHighlightMandatoryFieldErrors() {
        syscoLabUIOgm.waitTillElementLoaded(btnContinue);
        syscoLabUIOgm.click(btnContinue);
        return isRequiredFieldHasValidationError(txtPostalCode) &&
                isRequiredFieldHasValidationError(drpStates) &&
                isRequiredFieldHasValidationError(txtCity) ;
    }

    public static void enterPostalCode(String postCode){
        syscoLabUIOgm.sendKeys(txtPostalCode, postCode);
        syscoLabUIOgm.waitTillElementLoaded(drpPostCode);
        syscoLabUIOgm.click(syscoLabUIOgm.findElements(drpPostCodeList).get(0));
    }
    public static void enterPhoneNumber(String phoneNumber){
        syscoLabUIOgm.sendKeys(txtPhoneNumber, phoneNumber);
    }

    public void enterStreetAddress1(String streetAdress1) {
        syscoLabUIOgm.sendKeys(txtStreetAddress1, streetAdress1);
    }

    public void clickContinue() {
        syscoLabUIOgm.click(btnContinue);
    }

    public void clickOnCardType(By locator) {
        syscoLabUIOgm.clickWithJavascript(locator);
    }
    public void selectCardPayment(String paymentType) {
        if ("card".equals(paymentType)) {
            clickOnCardType(rdBtnCardPayment);
        } else if ("paypal".equals(paymentType)) {
            clickOnCardType(rdBtnPaypalPayment);
        } else if ("afterPay".equals(paymentType)) {
            clickOnCardType(rdBtnAfterPayPayment);
        }
    }

    public boolean isCartPaymentFieldsAvailable() {
        return syscoLabUIOgm.isDisplayed(txtCreditCard) &&
                syscoLabUIOgm.isDisplayed(txtExpirationMonth) &&
                syscoLabUIOgm.isDisplayed(txtExpirationYear) &&
                syscoLabUIOgm.isDisplayed(txtCvv);
    }

    public boolean isEmptyRequiredFieldHasErrorMessage() {
        return false;
    }
}

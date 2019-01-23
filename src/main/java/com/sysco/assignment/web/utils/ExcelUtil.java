package com.sysco.assignment.web.utils;

import com.sysco.assignment.web.data.CartData;
import com.sysco.assignment.web.data.CheckoutData;
import com.sysco.assignment.web.functions.Checkout;
import com.syscolab.qe.core.excelUtils.ReadExcel;
import com.sysco.assignment.web.data.LoginData;

/**
 * Created by Rifad on 3/6/18.
 */
public class ExcelUtil {


    public static LoginData getLoginData(String key) {
        Object loginObject = ReadExcel.getDataFromExcelSheet(key, System.getProperty("user.dir") + "/src/main/resources/testData/TestData.xls", 0, LoginData.class);
        LoginData loginData = (LoginData) loginObject;
        return loginData;
    }

    public static CartData getCartData(String key) {
        Object cartObject = ReadExcel.getDataFromExcelSheet(key, System.getProperty("user.dir") + "/src/main/resources/testData/TestData.xls", 1, CartData.class);
        CartData cartData = (CartData) cartObject;
        return cartData;
    }

    public static CheckoutData getCheckoutData(String key) {
        Object checkoutObject = ReadExcel.getDataFromExcelSheet(key, System.getProperty("user.dir") + "/src/main/resources/testData/TestData.xls", 2, CheckoutData.class);
        CheckoutData checkoutData = (CheckoutData) checkoutObject;
        return checkoutData;
    }

}

package com.sysco.assignment.web.utils;

import com.syscolab.qe.core.reporting.SyscoLabListener;
import com.syscolab.qe.core.reporting.SyscoLabQCenter;
import com.syscolab.qe.core.reporting.SyscoLabReporting;
import com.sysco.assignment.web.common.Constants;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(SyscoLabListener.class)
public class TestBase {
    private SyscoLabListener testListeners;
    private SyscoLabQCenter syscoLabQCenter;

    @BeforeClass
    public void init() {

        testListeners = new SyscoLabListener();
        syscoLabQCenter = new SyscoLabQCenter();
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Test Running " + this.getClass().toString());
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp(ITestContext iTestContext) {
        try {
            syscoLabQCenter.setProjectName(Constants.TEST_PROJECT);
            syscoLabQCenter.setEnvironment(Constants.TEST_ENV);

            syscoLabQCenter.setRelease(Constants.TEST_RELEASE);
            syscoLabQCenter.setModule(iTestContext.getAttribute("feature").toString());
            syscoLabQCenter.setFeature(iTestContext.getAttribute("feature").toString());
            syscoLabQCenter.setClassName(iTestContext.getClass().getName());

            if (Constants.UPDATE_DASHBOARD)
                SyscoLabReporting.generateJsonFile(SyscoLabListener.getResults(), syscoLabQCenter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

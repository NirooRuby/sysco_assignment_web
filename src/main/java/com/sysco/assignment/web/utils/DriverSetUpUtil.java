package com.sysco.assignment.web.utils;

import com.syscolab.qe.core.ui.SyscoLabCapabilityUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Rifad on 3/8/18.
 */
public class DriverSetUpUtil {

    DriverSetUpUtil() {

    }


    //Please add your required drivers here
    public static void setToRunLocally() {
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/windows/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/" + System.getProperty("os.arch") + "/chromedriver");
        }
    }


    public static void setToRunLocally(String path) {
        System.setProperty("webdriver.chrome.driver", path + System.getProperty("os.arch") + "/chromedriver");
    }

    public static Capabilities setToRunRemotely(String browser) {
        DesiredCapabilities capabilities;
        System.setProperty("hub.url", "http://10.1.19.213:4444/wd/hub");
        capabilities = SyscoLabCapabilityUtil.getPCCapabilities("WIN10", "Chrome");
        if (BrowserType.CHROME.equalsIgnoreCase(browser)) {
            capabilities.setBrowserName(BrowserType.CHROME);
        } else if (BrowserType.FIREFOX.equalsIgnoreCase(browser)) {
            capabilities.setBrowserName(BrowserType.FIREFOX);
        }
        return capabilities;
    }

    public static Capabilities setToRunRemotely(String hubUrl, String OS, String browser) {
        DesiredCapabilities capabilities;
        System.setProperty("hub.url", hubUrl);
        capabilities = SyscoLabCapabilityUtil.getPCCapabilities(OS, browser);
        if (BrowserType.CHROME.equalsIgnoreCase(browser)) {
            capabilities.setBrowserName(BrowserType.CHROME);
        } else if (BrowserType.FIREFOX.equalsIgnoreCase(browser)) {
            capabilities.setBrowserName(BrowserType.FIREFOX);
        }

        return capabilities;
    }
}

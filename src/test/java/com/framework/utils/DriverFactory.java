package com.framework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public class DriverFactory {
    private static Logger logger = Logger.getLogger(DriverFactory.class);
    private static WebDriver driver = null;
    private static WebDriver getDriverSingleton(String browser){
        try {
            if(driver==null){
                synchronized (DriverFactory.class){
                    if (driver == null) {
                        driver = OpenBrowser(browser);
                    }
                }
            }
        }catch (Exception e){
            logger.error(e);
            throw e;
        }
        return driver;
    }


    private static WebDriver ChromeDriverFactory(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        logger.info("Open Chrome Browser");
        return new ChromeDriver();
    }

    private static WebDriver FirefoxFactory(){
        System.setProperty("webdriver.geckodriver.driver","src/test/resources/geckodriver.exe");
        logger.info("Open Firefox Browser");
        return new FirefoxDriver();
    }

    private static WebDriver IEFactory(){
        // ignore the protected mode setting in IE
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        // ignore zoom setting
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
        logger.info("Open IE Browser");
        return new InternetExplorerDriver(capabilities);
    }

    private static  WebDriver OpenBrowser(String browserName){
        if(browserName.equalsIgnoreCase("Firefox")){
            return FirefoxFactory();
        }else if(browserName.equalsIgnoreCase("Chrome")){
            return ChromeDriverFactory();
        }else if(browserName.equalsIgnoreCase("IE")){
            return IEFactory();
        }else{
//            System.out.println("WRONG BROWSER!! please check your browser name");
            logger.warn("wrong browser name, please check. Open default chrome browser for you");
            return ChromeDriverFactory();
        }
    }

    public static WebDriver getNewDriver(String browser){
        return OpenBrowser(browser);
    }
    public static WebDriver getSingletonDriver(){return driver;}

}

package com.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
    public WebDriver ChromeDriverFactory(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        return new ChromeDriver();
    }

    public  WebDriver FirefoxFactory(){
        System.setProperty("webdriver.geckodriver.driver","src/test/resources/geckodriver.exe");
        return new FirefoxDriver();
    }

    public  WebDriver IEFactory(){
        // ignore the protected mode setting in IE
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        // ignore zoom setting
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
        return new InternetExplorerDriver(capabilities);
    }
    public  WebDriver OpenBrowser(String browserName){
        if(browserName.equalsIgnoreCase("Firefox")){
            return FirefoxFactory();
        }else if(browserName.equalsIgnoreCase("Chrome")){
            return ChromeDriverFactory();
        }else if(browserName.equalsIgnoreCase("IE")){
            return IEFactory();
        }else{
            System.out.println("WRONG BROWSER!! please check your browser name");
            return ChromeDriverFactory();
        }
    }
}

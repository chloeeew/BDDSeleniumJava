package com.framework.base;
import com.framework.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    Logger logger = Logger.getLogger(BaseTest.class);
    protected WebDriver driver = null;

    public void toURL(String browser,String url){
        driver = DriverFactory.getDriverSingleton(browser);
        driver.manage().deleteAllCookies();
        driver.get(url);
        logger.info("heading to " + url);
    }
    public void maximizeWindow(){
        driver.manage().window().maximize();
        logger.info("maximize browser window");
    }
    public void setImplicitWait(long secondTime){
        driver.manage().timeouts().implicitlyWait(secondTime, TimeUnit.SECONDS);
    }

    public void quitDriver() throws Exception{
        logger.info("browser is closing");
        driver.quit();
    }
}

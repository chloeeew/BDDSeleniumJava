package com.framework.utils;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotUtil {
    static Logger logger = Logger.getLogger(ScreenshotUtil.class);
    public static void getScreenshotAsFile(WebDriver driver,String testcaseName) throws IOException {
        // set file and directory
        String picFile="";
        try {
            Date date = new Date();
            String picDir = "C:\\errorScreenshot\\"+ DateUtils.getDateString(date);
            FileUtil.createDir(picDir);
            picFile = picDir +"\\"+ DateUtils.getTimeString(date)+"-"+testcaseName+".png";

            TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File desFile = new File(picFile);
            FileUtils.copyFile(srcFile,desFile);
        }catch(IOException ioException){
            logger.error("fail to take screenshot with file:"+picFile);
            logger.error(ioException);
            throw ioException;
        }


    }

    public static byte[] getScreenshotAsByte(WebDriver driver){
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        return takeScreenshot.getScreenshotAs(OutputType.BYTES);
    }

    public static void sendScreenshotToReportPortal(WebDriver driver, String testcaseName) throws IOException {
        try{
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            ReportPortalMessage message = new ReportPortalMessage(srcFile,testcaseName);
            ReportPortal.emitLog(message,"error",new Date());
        } catch (IOException ioException) {
            logger.error("fail to send screenshot to report portal with test case"+testcaseName);
            logger.error(ioException);
            throw ioException;
        }
    }


}

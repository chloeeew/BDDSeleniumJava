package com.demo.listener;

import com.demo.utils.DriverFactory;
import com.demo.utils.ScreenshotUtil;
import com.epam.reportportal.message.ReportPortalMessage;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.io.IOException;

public class TestFailListener implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        iHookCallBack.runTestMethod(iTestResult);
        if(iTestResult.getThrowable() != null){
            try {
                WebDriver currentDriver = DriverFactory.getCurrentDriver();
                ScreenshotUtil.getScreenshotAsFile(currentDriver,iTestResult.getTestName());
                ScreenshotUtil.sendSreenshotToReportPortal(currentDriver,iTestResult.getTestName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

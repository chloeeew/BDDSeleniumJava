package com.framework.listener;

import com.framework.base.BaseTest;
import com.framework.utils.ScreenshotUtil;
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
                WebDriver currentDriver = BaseTest.driver;
                ScreenshotUtil.getScreenshotAsFile(currentDriver,iTestResult.getMethod().getMethodName());
                ScreenshotUtil.sendScreenshotToReportPortal(currentDriver,iTestResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

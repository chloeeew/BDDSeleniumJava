package com.test.testrunner;


import com.framework.base.BaseTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterMethod;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/test/features"},
        glue = "com.test.stepdefinition",
        monochrome = true
)
public class EcommerceRunner extends AbstractTestNGCucumberTests {
    @AfterMethod
    public void teardown(){
        BaseTest.driver.quit();
    }
}

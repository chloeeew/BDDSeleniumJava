package com.demo.testcases;

import com.demo.base.BaseTest;
import com.demo.data.Constants;
import com.demo.pages.IndexPage;
import com.demo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestRegister extends BaseTest {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        this.driver = OpenBrowser("chrome");
        driver.get(Constants.ECOMMERCE_URL);
        // maximize window
        driver.manage().window().maximize();
        // set implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void test_user_registration() throws Exception {
        // click sign in button
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
        // enter your email address in 'Create and account' section(Scroll to the element first)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.send_text_to_create_account_by_email("t123@qq.com");

        // click on 'Create an Account button'
        loginPage.click_create_an_account_button();
        Assert.assertTrue(loginPage.is_valid_create_account_email());
        // enter your personal information, address and contact info
        loginPage.create_personal_info_for_register(0,"Chloe","Www",
                "ddeji","EFF","dfejiw","23345","poi","3034923");
        // Click on Register Button
        loginPage.click_register_button();
        // Validate that user is created
    }

    @AfterTest
    public void teardown() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }

}

package com.framework.testcases;

import com.framework.base.BaseTest;
import com.framework.data.Constants;
import com.framework.pages.HomePage;
import com.framework.pages.IndexPage;
import com.framework.pages.LoginPage;
import com.framework.utils.Assertion;
import org.testng.annotations.*;

public class TestRegister extends BaseTest {
    @BeforeTest
    public void setup(){
        toURL("chrome",Constants.ECOMMERCE_URL);
        maximizeWindow();
        setImplicitWait(15);
    }

    @Test
    public void test_user_registration() throws Exception {
        // click sign in button
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
        // enter your email address in 'Create and account' section(Scroll to the element first)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.send_text_to_create_account_by_email("t129@qq.com");

        // click on 'Create an Account button'
        loginPage.click_create_an_account_button();
        Assertion.assertTrue(loginPage.is_valid_create_account_email());
        // enter your personal information, address and contact info
        loginPage.create_personal_info_for_register(0,"Www",
                "ddeji","aaa22222","dfejiw","23345","87979","3034923");
        // Click on Register Button
        loginPage.click_register_button();

        HomePage homePage = new HomePage(driver);
        Assertion.assertTrue(homePage.check_account_is_welcomed());
        // Validate that user is created
    }

    @AfterTest
    public void teardown() throws Exception {
        quitDriver();
    }

}

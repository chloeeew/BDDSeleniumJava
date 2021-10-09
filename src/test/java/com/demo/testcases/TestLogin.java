package com.demo.testcases;
import com.demo.base.BaseTest;
import com.demo.data.Constants;
import com.demo.pages.HomePage;
import com.demo.pages.IndexPage;
import com.demo.pages.LoginPage;
import com.demo.utils.Assertion;
import org.testng.annotations.*;

public class TestLogin extends BaseTest {
    @BeforeTest
    public void setup(){
        toURL("chrome", Constants.ECOMMERCE_URL);
        maximizeWindow();
        setImplicitWait(15);
    }
    @Test
    public void test_user_login(){
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login_by_email_and_password("t124@qq.com","aaa22222");
        HomePage homePage = new HomePage(driver);
        Assertion.assertTrue(homePage.check_account_is_welcomed());
        Assertion.assertTrue(homePage.check_account_owner_by_string("Www ddeji"));

    }

    @AfterTest
    public void teardown() throws Exception {
        quitDriver();
    }
}

package com.framework.testcases;
import com.framework.base.BaseTest;
import com.framework.data.Constants;
import com.framework.pages.HomePage;
import com.framework.pages.IndexPage;
import com.framework.pages.LoginPage;
import com.framework.utils.Assertion;
import org.testng.annotations.*;

public class TestLogin extends BaseTest {
    @BeforeMethod
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
        loginPage.login_type_email_and_password("t124@qq.com","aaa22222");
        loginPage.click_login_button();
        HomePage homePage = new HomePage(driver);
        Assertion.assertTrue(homePage.check_account_is_welcomed());
        Assertion.assertTrue(homePage.check_account_owner_by_string("Www ddeji"));
    }

    @Test
    public void test_user_login_pwd_lack_one_character(){
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login_type_email_and_password("t124@qq.com","aaa2222");
        loginPage.click_login_button();
        Assertion.assertEqualString("Authentication failed.",loginPage.get_alert_danger_tip());
    }


    @AfterMethod
    public void teardown() {
        quitDriver();
    }


}

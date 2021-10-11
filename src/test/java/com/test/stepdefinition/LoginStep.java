package com.test.stepdefinition;


import com.framework.base.BaseTest;
import com.framework.data.Constants;
import com.framework.pages.HomePage;
import com.framework.pages.IndexPage;
import com.framework.pages.LoginPage;
import com.framework.utils.Assertion;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends BaseTest {
    LoginPage loginPage;

    @Before
    public void setup(){
        toURL("chrome", Constants.ECOMMERCE_URL);
        maximizeWindow();
        setImplicitWait(15);
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
        loginPage = new LoginPage(driver);
    }


    @Given("Open {string} browser with project website url")
    public void openBrowserWithWebsiteUrl(String browser) {
        toURL(browser, Constants.ECOMMERCE_URL);
        maximizeWindow();
        setImplicitWait(15);
    }

    @When("Click sign in in Index page")
    public void clickSignInButtonInIndexPage() {
        IndexPage indexPage = new IndexPage(driver);
        indexPage.click_sign_in_button();
    }

    @Then("Login page is displayed")
    public void loginPageIsDisplayed() {
        loginPage = new LoginPage(driver);
    }

    @Given("Type {string} as username and {string} as password")
    public void type_as_username_and_as_password(String username, String pwd) {
        loginPage.login_type_email_and_password(username,pwd);
    }

    @When("Click Sign in button")
    public void click_sign_in_button() {
        loginPage.click_login_button();
    }

    @Then("Account {string} is shown in right top corner and being welcomed in Home page")
    public void account_is_shown_in_right_top_corner_and_being_welcomed_in_home_page(String accountName) {
        HomePage homePage = new HomePage(driver);
        Assertion.assertTrue(homePage.check_account_is_welcomed());
        Assertion.assertTrue(homePage.check_account_owner_by_string(accountName));
    }

    @Then("An alert tip {string} is shown in login page")
    public void an_alert_tip_is_shown_in_login_page(String alert) {
        Assertion.assertEqualString(alert,loginPage.get_alert_danger_tip());
    }

//    @After
//    public void teardown() throws Exception{
//        Thread.sleep(3000);
//        quitDriver();
//    }


}

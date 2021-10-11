package com.test.stepdefinition;

import com.framework.base.BaseTest;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import com.framework.utils.Assertion;
import com.framework.utils.DataUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStep extends BaseTest {
    LoginPage loginPage;
    @Given("Type new email to create an account")
    public void type_new_email_to_create_an_account() throws InterruptedException {
        loginPage = new LoginPage(driver);
        String email = DataUtil.getRandomEmail(4,7);
        loginPage.send_text_to_create_account_by_email(email);

    }
    @When("Click on Create an Account button")
    public void click_on_create_an_account_button() {
        loginPage.click_create_an_account_button();
    }

    @Then("Display account is valid")
    public void display_account_is_valid() {
        Assertion.assertTrue(loginPage.is_valid_create_account_email());
    }

    @Given("Type {string},{string},{string},{string},{string},{string},{string},{string} in Register form")
    public void typeInRegisterForm(String gender, String firstname, String lastname, String password, String address,
                                   String city, String zipcode,String phone) throws InterruptedException {
        int genderNum = 0;
        if(gender.equalsIgnoreCase("man")){
            genderNum = 1;
        }
        loginPage.create_personal_info_for_register(genderNum,firstname,
                lastname,password,address,city,zipcode,phone);
    }

    @When("Click on register button")
    public void clickOnRegisterButton() {
        loginPage.click_register_button();
    }

    @Then("Account is being welcomed in Home page")
    public void accountIsBeingWelcomedInHomePage() {
        HomePage homePage = new HomePage(driver);
        Assertion.assertTrue(homePage.check_account_is_welcomed());
    }

    @Given("Type new valid email to create an account")
    public void typeNewValidEmailToCreateAnAccount() throws InterruptedException{
        loginPage = new LoginPage(driver);
        String email = DataUtil.getRandomEmail(4,8);
        loginPage.send_text_to_create_account_by_email(email);
        loginPage.click_create_an_account_button();
    }
}

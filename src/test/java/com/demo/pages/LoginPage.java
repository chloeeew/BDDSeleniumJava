package com.demo.pages;

import com.demo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    By emailLoginLocator = By.cssSelector("#email");
    By pwdLoginLocator = By.cssSelector("#passwd");
    By loginBtnLocator = By.cssSelector("#SubmitLogin");
    By emailAddressLocator = By.cssSelector("#email_create");
    By trueCreateEmailLocator = By.cssSelector(".form-group.form-ok");
    By createButtonLocator = By.cssSelector("#SubmitCreate");
    By titleMrRadioLocator = By.cssSelector("input#id_gender1");
    By titleMrsRadioLocator = By.cssSelector("input#id_gender2");
    By firstnameInputLocator = By.cssSelector("#customer_firstname");
    By lastnameInputLocator = By.cssSelector("#customer_lastname");
    By pwdInputLocator = By.cssSelector("#passwd");
    By firstnameAddressLocator = By.cssSelector("#firstname");
    By lastnameAddressLocator = By.cssSelector("#lastname");
    By addressLocator = By.cssSelector("#address1");
    By cityLocator = By.cssSelector("#city");
    By stateLocator = By.cssSelector("#id_state");
    By zipLocator = By.cssSelector("#postcode");
    By countryLocator = By.cssSelector("#id_country");
    By phoneLocator = By.cssSelector("#phone_mobile");
    By registerButtonLocator = By.cssSelector("#submitAccount");
    By alertDangerTipsLct = By.xpath("//div[contains(@class,'alert-danger')]/ol/li");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login_by_email_and_password(String email,String pwd){
        input_text(emailLoginLocator,email);
        input_text(pwdLoginLocator,pwd);
        click_element(loginBtnLocator);
    }

    public void send_text_to_create_account_by_email(String emailAddress) throws InterruptedException {
        WebElement createAccountEle = driver.findElement(emailAddressLocator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        String scriptScroll = "arguments[0].scrollIntoViewIfNeeded(true)";
        jsExecutor.executeScript(scriptScroll,createAccountEle);
        Thread.sleep(1000);
        createAccountEle.sendKeys(emailAddress);
    }

    public boolean is_valid_create_account_email(){
        WebElement trueEmailEle = driver.findElement(trueCreateEmailLocator);
        boolean result = trueEmailEle.isDisplayed();
        logger.info("check create account email input box is valid: "+result);
        return result;
    }


    public void click_create_an_account_button(){
        click_element(createButtonLocator);

    }

    public void create_personal_info_for_register(int gender, String custom_firstname,
                                                  String custom_lastname, String pwd, String address, String city,
                                                  String zipcode, String mobile_phone) throws InterruptedException {
        By genderLocator;
        if(gender==0){
            genderLocator = titleMrsRadioLocator;
        }else {
            genderLocator = titleMrRadioLocator;
        }
        click_element(genderLocator);
        input_text(firstnameInputLocator,custom_firstname,"input customer first name");
        input_text(lastnameInputLocator,custom_lastname,"input customer last name");
        input_text(pwdInputLocator,pwd);

        input_text(addressLocator,address,"input address locator");
        scroll_to_top(addressLocator);
        Thread.sleep(2000);
        select_from_radio_drop_down(countryLocator,"United States");

        select_from_radio_drop_down(stateLocator,"Utah");
        input_text(zipLocator,zipcode);
        input_text(cityLocator,city);
        input_text(phoneLocator,mobile_phone);

    }

    public void click_register_button(){
        click_element(registerButtonLocator);
    }

    public String get_alert_danger_tip(){
        return get_element_text(alertDangerTipsLct);
    }
}

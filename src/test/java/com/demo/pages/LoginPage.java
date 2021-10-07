package com.demo.pages;

import com.demo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
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

    public LoginPage(WebDriver driver) {
        super(driver);
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
        return trueEmailEle.isDisplayed();
    }


    public void click_create_an_account_button(){
        WebElement createButtonEle = driver.findElement(createButtonLocator);
        createButtonEle.click();

    }

    public void create_personal_info_for_register(int gender,String custom_firstname,
                                                  String custom_lastname,String address,String city,String states,
                                                  String zipcode,String country,String mobile_phone){
        By genderLocator;
        if(gender==0){
            genderLocator = titleMrsRadioLocator;
        }else {
            genderLocator = titleMrRadioLocator;
        }
        click_element(driver,genderLocator);
        input_text(driver,firstnameInputLocator,custom_firstname);
        input_text(driver,lastnameInputLocator,custom_lastname);

        input_text(driver,addressLocator,address);

        input_text(driver,cityLocator,city);
        input_text(driver,zipLocator,zipcode);
        select_from_radio_drop_down(driver,countryLocator,"usa");
        select_from_radio_drop_down(driver,cityLocator,"aaa");
        input_text(driver,phoneLocator,mobile_phone);

    }

    public void click_register_button(){
        click_element(driver,registerButtonLocator);
    }
}
